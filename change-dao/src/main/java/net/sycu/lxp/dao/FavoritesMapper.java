package net.sycu.lxp.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import net.sycu.lxp.pojo.Favorites;
import net.sycu.lxp.vo.FavoritesVo;

@Repository("favoritesMapper")
public interface FavoritesMapper {
	
    int insert(Favorites favorite);
    
    List<Favorites> getFavoritesList(FavoritesVo favoritesVo);
    
    Integer getFavoritesCount(FavoritesVo favoritesVo);
    
    void deleteFavoritesByIds(String[] ids);

	List<Favorites> getFavorites(Favorites favorites);
}