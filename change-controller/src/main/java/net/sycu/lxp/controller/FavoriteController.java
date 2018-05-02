package net.sycu.lxp.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import net.sycu.lxp.common.Constant;
import net.sycu.lxp.pojo.Favorites;
import net.sycu.lxp.pojo.User;
import net.sycu.lxp.service.IFavoritesService;
import net.sycu.lxp.utils.StringUtils;
import net.sycu.lxp.vo.DataVo;
import net.sycu.lxp.vo.FavoritesVo;

@Controller
@RequestMapping("/favorites/")
public class FavoriteController {
	
    @Autowired
    @Qualifier("favoriteService")
    IFavoritesService favoritesService;
	
    /**
     *  @Description: 获取收藏
     *  @author lxp
     *  @method deleteFavoritess
     *  params  [favoritesVo] 收藏对象
     *  @return 数据对象
     *  @exception
     **/
	@RequestMapping("getFavoritesList")
	@ResponseBody
	public DataVo getFavorites(HttpServletRequest request,@RequestBody FavoritesVo favoritesVo) {
		
		User user = (User) request.getSession().getAttribute(Constant.CURRENT_USER);
		
		if(user != null) {
			favoritesVo.setUserId(user.getId());
		}
		
        DataVo dataVo = new DataVo();
        Integer pageNum = favoritesVo.getPageNum();
        //设置查询开始的条数(就是从哪条开始查询)
        if(pageNum != null) {
            favoritesVo.setPageNum((pageNum-1)*favoritesVo.getPageSize());
        }
        try {
            List<Favorites> favoritesList = favoritesService.getFavoritesList(favoritesVo);
            dataVo.setDatalist(favoritesList);
            dataVo.setCode(Constant.DataCode.SUCCESS);
            dataVo.setMsg("数据获取成功");
            if (favoritesList != null) {
                dataVo.setCount(favoritesService.getFavoritesCount(favoritesVo));
            } else {
                dataVo.setCount(Constant.ZERO_NUM);
            }
        } catch (Exception e) {
            dataVo.setCode(Constant.DataCode.FAIL);
            dataVo.setMsg("数据获取失败");
            e.printStackTrace();
        }
        return dataVo;
	}
	
	@RequestMapping("getFavoritesInfo")
	@ResponseBody
	public Map<String, String> getFavoritesInfo(HttpServletRequest request,@RequestBody Favorites favorites) {
		Map<String, String> msgMap = new HashMap<String, String> ();
		String message = Constant.FavoritesConstants.IS_NOT_COLLECT;
		String favoritesId = "";
		User user = (User) request.getSession().getAttribute(Constant.CURRENT_USER);
		if(user != null) {
			favorites.setUserId(user.getId());
		}
		List<Favorites> favoritesList = favoritesService.getFavorites(favorites);
		if (favoritesList != null && favoritesList.size() > 0) {
			message = Constant.FavoritesConstants.IS_COLLECT;
			favoritesId = favoritesList.get(0).getId();
		}
		msgMap.put("status", message);
		msgMap.put("favoritesId", favoritesId);
		return msgMap;
	}
	
    /**
     *  @Description: 保存收藏
     *  @author lxp
     *  params  [favorites] 收藏对象
     *  @return 操作信息
     **/
	@RequestMapping("save")
	@ResponseBody
	public Map<String, String> saveFavorites(HttpServletRequest request, @RequestBody Favorites favorites){
		
		Map<String, String> msgMap = new HashMap<String, String>();
		User user = (User) request.getSession().getAttribute(Constant.CURRENT_USER);
		if(user != null) {
			favorites.setUserId(user.getId());
		}
		try{
			favorites.setIsDeleted(Constant.DeleteStatus.IS_NOT_DELETE);
			favorites.setId(StringUtils.getUUID());
			favorites.setCreateTime(new Date());
			favoritesService.save(favorites);
			msgMap.put(Constant.AjaxStatus.AJAX_SUCCESS,"删除收藏信息成功");
		}catch(Exception e) {
			e.printStackTrace();
			msgMap.put(Constant.AjaxStatus.AJAX_FAIL,"删除收藏信息失败");
		}
		msgMap.put("favoritesId", favorites.getId());
		return msgMap;		
	}
	
    /**
     *  @Description: 删除收藏
     *  @author lxp
     *  @method deleteFavoritess
     *  params  [favorites] 收藏对象
     *  @return 操作信息
     *  @exception
     **/
    @RequestMapping("delete")
    @ResponseBody
    public Map<String, String> deleteFavoritess(@RequestBody Favorites favorites) {
        Map<String, String> msgMap = new HashMap<String, String>();
        String [] favoritesIds = null;
        if(StringUtils.isNotBlank(favorites.getIds())){
            favoritesIds = favorites.getIds().split(",");
        }
        try {
            if(favoritesIds != null && favoritesIds.length > 0) {
            	favoritesService.deleteFavoritesByIds(favoritesIds);
            }
            msgMap.put(Constant.AjaxStatus.AJAX_SUCCESS,"删除收藏信息成功");
        } catch (Exception e) {
            msgMap.put(Constant.AjaxStatus.AJAX_FAIL,"删除收藏信息失败");
        }
        return msgMap;
    }
	
}
