package com.assadosman.Trading.App.model.Assets;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import com.assadosman.Trading.App.model.Assets.Data.PriceDay;
import com.assadosman.Trading.App.model.Assets.Data.Response;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Random;

@Service
public class AssetsService {
    private AssetRepo assetRepo;
    private ObjectMapper objectMapper;

    private RestTemplate restTemplate;

    public AssetsService(AssetRepo assetRepo, RestTemplate restTemplate) {
        this.assetRepo = assetRepo;
        this.objectMapper = new ObjectMapper();
        this.restTemplate = restTemplate;
    }

    // will be used in case limit of requests has been reached to simulate real data
    public static double generateRandomStockPrice(double basePrice, double volatility) {
        Random random = new Random();
        double changePercent = (random.nextDouble() * 2 - 1) * volatility;
        return Math.round((basePrice * (1 + changePercent / 100)) * 100.0) / 100.0;
    }


    public Double getCurrentPrice(String id) throws JsonProcessingException {
        Optional<AssetEntity> doc = assetRepo.findById(id);
        String json = doc.get().getPrices();
        List<Double> prices = objectMapper.readValue(json, new TypeReference<List<Double>>() {});


        if(prices.isEmpty()){
            double randomPrice = generateRandomStockPrice(100.0, 2.0);
            prices.add(randomPrice);
        }
        return prices.get(0);
    }

    public Response fetchDataFromApi(String name) {
        String apiUrl = "http://api.marketstack.com/v1/eod?access_key=de2cb7e86056e2d9651416674df217b4&symbols=" + name;

        Response response = restTemplate.getForObject(apiUrl, Response.class);

        if (response == null || response.getData() == null) {
            throw new RuntimeException("No data received from API for symbol: " + name);
        }


        return response;
    }

    public Optional<AssetEntity> findByID(String name){
        return assetRepo.findById(name);
    }

    public void save(AssetEntity asset){
        assetRepo.save(asset);
    }

}
