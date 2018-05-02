package net.sycu.lxp.service;

import java.util.List;

import net.sycu.lxp.pojo.Favorites;
import net.sycu.lxp.vo.FavoritesVo;

/**
 * 用户收藏
 * @author lxp
 *
 */
public interface IFavoritesService {
	
    /**
     *  @Description: 获取收藏记录数
     *  @author lxp
     *  params  [goodsVo] 收藏对象
     *  @exception
     **/
	
	List<Favorites> getFavoritesList(FavoritesVo favoritesVo);
	
	
	
    /**
     *  @Description: 获取收藏记录数
     *  @author lxp
     *  params  [favoritesVo] 收藏对象
     *  @exception
     **/
	Integer getFavoritesCount(FavoritesVo favoritesVo);
	
	
    /**
     *  @Description: 批量删除收藏数据
     *  @author lxp
     *  params  [favoritesIds] 收藏id集
     *  @exception
     **/
	void deleteFavoritesByIds(String[] favoritesIds);
	
	/**
	 * 获取收藏信息
	 * @param favorites
	 * @return
	 */
	List<Favorites> getFavorites(Favorites favorites);


	/**
	 * 保存收藏信息
	 * @param favorites
	 */
	void save(Favorites favorites);
	
}
