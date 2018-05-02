package net.sycu.lxp.service;

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
import net.sycu.lxp.dao.GoodsMapper;
import net.sycu.lxp.pojo.Goods;
import net.sycu.lxp.pojo.GoodsCategory;
import net.sycu.lxp.pojo.User;
import net.sycu.lxp.utils.FileUploadDownUtils;
import net.sycu.lxp.utils.StringUtils;
import net.sycu.lxp.vo.GoodsVo;

@Service("goodsService")
@Transactional
public class GoodsServiceImpl implements IGoodsService {
	
    @Autowired
    @Qualifier("goodsMapper")
    GoodsMapper goodsMapper;
    
    @Autowired
    @Qualifier("userService")
    IUserService userService;
    
    @Autowired
    @Qualifier("goodsCategoryService")
    IGoodsCategoryService goodsCategoryService;
	
	public List<Goods> getGoodList(HttpServletRequest request, GoodsVo goodsVo) {
		
		List<Goods> goodsList = goodsMapper.getGoodsList(goodsVo);		
		return packGoods(request, goodsList);
	}
	
	public List<Goods> getGoodsByCategory(HttpServletRequest request, GoodsVo goodsVo) {
		List<Goods> goodsList = goodsMapper.getGoodsByCategory(goodsVo);
		return packGoods(request, goodsList);
	}

	public List<Goods> getGoods(Goods goods) {
		return goodsMapper.getGoods(goods);
	}

	public void update(Goods goods) {
		goodsMapper.updateByGoodsSelective(goods);
	}

	public void save(Goods goods) {
		goodsMapper.insert(goods);
	}

	public Integer getGoodsCount(GoodsVo goodsVo) {
		return goodsMapper.getGoodsCount(goodsVo);
	}

	public void deleteGoodsByIds(String[] goodsIds) {
		goodsMapper.deleteGoodsByIds(goodsIds);
	}

	public void saveGoods(HttpServletRequest request) {
		Goods goods = new Goods();
		FileUploadDownUtils utils = new FileUploadDownUtils();
		Map<String, String> uploadMsg = utils.uploadFiles(request);
		String goodsId = uploadMsg.get(Constant.SystemField.ID_ATTR);
		
		//保存商品信息
		goods.setName(uploadMsg.get(Constant.SystemField.NAME_ATTR));
		goods.setDescription(uploadMsg.get(Constant.SystemField.DESCRIPTION_ATTR));
		goods.setImagesSrc(uploadMsg.get(Constant.SystemField.IMAGES_SRC));
		
		if(StringUtils.isNotBlank(goodsId)) {
			//删除商品之前关联的类型信息
			goodsCategoryService.deleteGoodsCateByGoodsId(goodsId);
			goods.setUpdateTime(new Date());
			this.update(goods);
		}else {
			//保存商品信息
			goods.setIsDeleted(Constant.DeleteStatus.IS_NOT_DELETE);
			goods.setCreatorId(uploadMsg.get(Constant.SystemField.USER_ID_ATTR));
			goods.setId(StringUtils.getUUID());
			goods.setCreateTime(new Date());
			this.save(goods);
		}
		
		//关联新的类型信息
		GoodsCategory goodsCategory = new GoodsCategory();
		goodsCategory.setId(StringUtils.getUUID());
		goodsCategory.setCategoryId(uploadMsg.get(Constant.SystemField.CATEGORY_ATTR));
		goodsCategory.setGoodsId(goods.getId());
		goodsCategory.setIsDeleted(Constant.DeleteStatus.IS_NOT_DELETE);
		goodsCategoryService.saveGoodsCategory(goodsCategory);
		
	}
	
	/**
	 * 封装
	 * @param goodsList
	 * @return
	 */
	private List<Goods> packGoods (HttpServletRequest request, List<Goods> goodsList){
		if(goodsList != null && goodsList.size() > 0) {
			for (Goods goods:goodsList) {
				String imagesSrc = goods.getImagesSrc();
				//获取第一张图片
				if(StringUtils.isNotBlank(imagesSrc)) {
					String [] imagesSrcArr = imagesSrc.split(",");
					imagesSrc = imagesSrcArr[0];
				} else {
					//如果商品没有图片为商品设置一张默认图片
		            String basePath = request.getScheme()+"://"+request.getServerName()+":"
		                    +request.getServerPort()+request.getContextPath();
					imagesSrc = basePath + Constant.GoodsConstants.GOODS_IMAGE_SRC;
				}
				goods.setImagesSrc(imagesSrc);
			}
		}
		return goodsList;
	}

	public Integer getGoodsCountByCategory(GoodsVo goodsVo) {
		return goodsMapper.getGoodsCountByCategory(goodsVo);
	}

	public GoodsVo getGoodsInfo(HttpServletRequest request, Goods goods) { 
		
		User user = (User) request.getSession().getAttribute(Constant.CURRENT_USER);
		User sellerInfo = new User();
		String goodsType = Constant.GoodsConstants.BUYER_GOODS;
		Goods goodsInfo = new Goods();
		List<User> userList = new ArrayList<User>();
		
		List<Goods> goodsList = this.getGoods(goods);
		if(goodsList != null && goodsList.size()>0) {
			goodsInfo = goodsList.get(0);
			//获取申请交换人员的信息
			if(user != null) {
				if(user.getId().equals(goodsInfo.getCreatorId())) {						
					goodsType = Constant.GoodsConstants.SELLER_GOODS;
					userList = getUpdateUsers(goodsInfo.getUpdateUserId());
				}
			}
			//获取卖家信息
			if(StringUtils.isNotBlank(goodsInfo.getCreatorId())) {
				List<User> userListInfo = userService.getUsersByIds(new String [] {goodsInfo.getCreatorId()});
				if (userListInfo != null && userListInfo.size() > 0) {
					sellerInfo = userListInfo.get(0);
				}
			}
		}
		GoodsVo vo = new GoodsVo(goodsInfo);
		vo.setUserList(userList);
		vo.setGoodsType(goodsType);
		vo.setSellerInfo(sellerInfo);
		return vo;
	}
	
	/**
	 * 获取要求申请的用户信息
	 * @param updateUserId
	 */
	public List<User> getUpdateUsers(String updateUserId) {
		List<User> userList = new ArrayList<User>();
		if(StringUtils.isNotBlank(updateUserId)) {
			String [] updateUserIds = updateUserId.split(",");
			userList = userService.getUsersByIds(updateUserIds); 
		}
		return userList;
	}

	public Goods getGoods(String goodsId) {
		
    	Goods goods = new Goods();
    	goods.setId(goodsId);
    	List<Goods> goodsList = this.getGoods(goods);
    	if(goodsList != null && goodsList.size() > 0) {
    		goods = goodsList.get(0);
    	}
		return goods;
	}
	
}
