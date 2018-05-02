package net.sycu.lxp.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import net.sycu.lxp.common.Constant;
import net.sycu.lxp.dao.FavoritesMapper;
import net.sycu.lxp.pojo.Favorites;
import net.sycu.lxp.pojo.Goods;
import net.sycu.lxp.utils.StringUtils;
import net.sycu.lxp.vo.FavoritesVo;

@Service("favoriteService")
@Transactional
public class FavoritesServiceImpl implements IFavoritesService{
	
    @Autowired
    @Qualifier("favoritesMapper")
    FavoritesMapper favoritesMapper;
    
    @Autowired 
    @Qualifier("goodsService")
    IGoodsService goodsService;
	
	public List<Favorites> getFavoritesList(FavoritesVo favoritesVo) {
		List<Favorites> favoritesList =  favoritesMapper.getFavoritesList(favoritesVo);
		if (favoritesList != null) {
			for (Favorites favorites:favoritesList) {
				if (StringUtils.isNotBlank(favorites.getGoodsId())) {
					Goods goods = goodsService.getGoods(favorites.getGoodsId());
					favorites.setGoods(goods); 
					if(goods != null) {
						String imagesSrcs = goods.getImagesSrc();
						if (StringUtils.isNotBlank(imagesSrcs)) {
							String [] imagesSrcArr = imagesSrcs.split(",");
							goods.setImagesSrc(imagesSrcArr[0]);
						} else {
							goods.setImagesSrc(Constant.GoodsConstants.GOODS_IMAGE_SRC);
						}
					}
				}
			}
		}
		return favoritesList;
	}

	public Integer getFavoritesCount(FavoritesVo favoritesVo) {
		return favoritesMapper.getFavoritesCount(favoritesVo);
	}

	public void deleteFavoritesByIds(String[] favoritesIds) {
		favoritesMapper.deleteFavoritesByIds(favoritesIds);
	}

	public List<Favorites> getFavorites(Favorites favorites) {
		return favoritesMapper.getFavorites(favorites);
	}

	public void save(Favorites favorites) {
		favoritesMapper.insert(favorites);
	}
	
}
