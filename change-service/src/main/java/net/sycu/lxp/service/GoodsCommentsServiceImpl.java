package net.sycu.lxp.service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import net.sycu.lxp.common.Constant;
import net.sycu.lxp.dao.GoodsCommentsMapper;
import net.sycu.lxp.pojo.Goods;
import net.sycu.lxp.pojo.GoodsComments;
import net.sycu.lxp.pojo.Reply;
import net.sycu.lxp.pojo.User;
import net.sycu.lxp.utils.ConnectionUtils;
import net.sycu.lxp.utils.FileUploadDownUtils;
import net.sycu.lxp.utils.StringUtils;
import net.sycu.lxp.vo.CommentsVo;

@Service("commentsService")
@SuppressWarnings("unchecked")
@Transactional
public class GoodsCommentsServiceImpl implements IGoodsCommentsService {

	@Autowired
	@Qualifier("replyService")
	IReplyService replyService;
	
	@Autowired
	@Qualifier("userService")
	IUserService userService;
	
	@Autowired 
	@Qualifier("goodsService")
	IGoodsService goodsService;
	
	@Autowired
	@Qualifier("commentsMapper")
	GoodsCommentsMapper commentsMapper;
	
	public List<GoodsComments> getComments(CommentsVo commentsVo) {
		return commentsMapper.getComments(commentsVo);
	}

	public List<CommentsVo> getCommentsVo(CommentsVo commentsVo) {
		
		List<CommentsVo> voList = new ArrayList<CommentsVo>();
		ConnectionUtils connectionUtils = new ConnectionUtils();
        List<GoodsComments> comments = this.getComments(commentsVo);
        if (comments != null && comments.size() > 0) {
			List<String> commentsIds = (List<String>) connectionUtils.getIdList(comments,"getId",null);
			Map<String,List<Reply>> replyMap = replyService.getReply(commentsIds); 
			for (GoodsComments comment: comments) {
				CommentsVo vo = new CommentsVo(comment);
				//设置用户名称
				if(StringUtils.isNoneBlank(comment.getUserId())) {
					User userInfo = new User();
					userInfo.setId(comment.getUserId());
					List<User> userList = userService.getUser(userInfo);
					if(userList != null && userList.size() > 0) {
						User user = userList.get(0);
						vo.setUserName(user.getUserName());
					}
					if(vo.getCreateTime() != null) {
						//设置时间格式
						SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
						vo.setCreateTimeStr(df.format(vo.getCreateTime()));
					}
				}
				if(StringUtils.isNotBlank(comment.getGoodsId())) {
					Goods goods = goodsService.getGoods(comment.getGoodsId());
					if(StringUtils.isBlank(goods.getImagesSrc())) {
						goods.setImagesSrc(Constant.GoodsConstants.GOODS_IMAGE_SRC);
					}
					vo.setGoods(goods);
				}
				vo.setReplyList(replyMap.get(comment.getId()));
				voList.add(vo);
			}
		}
        return voList;
	}

	public void save(GoodsComments comments) {
		commentsMapper.insert(comments);
	}

	public void saveComments(HttpServletRequest request) {
		
		FileUploadDownUtils utils = new FileUploadDownUtils();
		Map<String, String> msgMap = utils.uploadFiles(request); 
		
		GoodsComments goodsComments = new GoodsComments();
		goodsComments.setCreateTime(new Date());
		goodsComments.setId(StringUtils.getUUID());
		goodsComments.setContent(msgMap.get(Constant.SystemField.DESCRIPTION_ATTR));
		goodsComments.setAnnexSrc(msgMap.get(Constant.SystemField.IMAGES_SRC));
		goodsComments.setGoodsId(msgMap.get(Constant.SystemField.GOODS_ID_ATTR));
		goodsComments.setUserId(msgMap.get(Constant.SystemField.USER_ID_ATTR));
		goodsComments.setIsDeleted(Constant.DeleteStatus.IS_NOT_DELETE);
		this.save(goodsComments);
		
	}

	public Integer getCommentsCount(CommentsVo commentsVo) {
		return commentsMapper.getCommentsCount(commentsVo);
	}

}
