package com.learning.spring.giflib.controller;

import com.learning.spring.giflib.data.GifRepository;
import com.learning.spring.giflib.model.Gif;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;


@Controller
public class GifController {
    @Autowired
    private GifRepository gifRepository;

    @RequestMapping("/")
    public String listGifs(ModelMap modelMap){
        List<Gif> allGifs = gifRepository.getAllGifs();
        modelMap.put("gifs", allGifs);
        return "home";
    }

    @RequestMapping( "/gif/{name}")
    public String gifDetails(@PathVariable String name, ModelMap modelMap) {
        Gif gif = gifRepository.findByName(name);
        modelMap.put("gif", gif);
        return "gif-details";
    }

    @RequestMapping({"/search", "/gif"})
    public String searchByGifName(@RequestParam String q, ModelMap modelMap){
        List<Gif> gifByName= gifRepository.searchGifByName(q);
        modelMap.put("gifByName", gifByName);
        return "search-results";
    }


    @RequestMapping("/favorites")
    public String listFavGifs(ModelMap modelMap) {
        List<Gif> gifs = gifRepository.getAllFavorites();
        modelMap.put("gifs", gifs);
        return "favorites";
    }
}
