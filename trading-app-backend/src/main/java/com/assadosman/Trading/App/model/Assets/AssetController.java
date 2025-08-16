package com.assadosman.Trading.App.model.Assets;

import com.assadosman.Trading.App.model.Assets.Data.PriceDay;
import com.assadosman.Trading.App.model.Assets.Data.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
public class AssetController {


    private AssetsService assetService;

    public AssetController(AssetsService assetService) {
        this.assetService = assetService;
    }

    @GetMapping(path="/api/assets/{name}")
    public ResponseEntity<AssetEntity> respondGetRequest(@PathVariable("name") String name){
        Response response;
        try{
            response = assetService.fetchDataFromApi(name);
        }catch(Exception e){
            response = new Response(new ArrayList<>(), null);
        }

        ArrayList<String> prices = new ArrayList<>();
        ArrayList<String> dates = new ArrayList<>();

        for(PriceDay day : response.getData()){
            prices.add(day.getOpen());
            dates.add("\""+day.getDate().substring(0, 10)+"\"");
        }


        Optional<AssetEntity> optionalAsset = assetService.findByID(name);
        if (optionalAsset.isPresent()){
            assetService.save(AssetEntity.builder()
                    .prices(prices.toString())
                    .dates(dates.toString())
                    .marketCap(0)
                    .name(name)
                    .build());
            return new ResponseEntity<>(optionalAsset.get(), HttpStatus.FOUND);
        }else{
            AssetEntity asset = AssetEntity.builder()
                    .prices(prices.toString())
                    .dates(dates.toString())
                    .marketCap(0)
                    .name(name)
                    .build();
            assetService.save(asset);
            return new ResponseEntity<>(asset, HttpStatus.CREATED);
        }
    }
}
