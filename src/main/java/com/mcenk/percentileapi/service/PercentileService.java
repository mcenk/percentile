package com.mcenk.percentileapi.service;


import com.mcenk.percentileapi.dto.SavePercentileRequest;
import com.mcenk.percentileapi.model.Child;
import com.mcenk.percentileapi.model.PercentileCalc;
import com.mcenk.percentileapi.repository.PercentileRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class PercentileService {

    private final PercentileRepository percentileRepository;
    private final ChildService childService;


    public PercentileService(PercentileRepository percentileRepository, ChildService childService) {
        this.percentileRepository = percentileRepository;
        this.childService = childService;
    }
    @Transactional // hata olursa veriyi geri toplasin
    public PercentileCalc savePercentile(SavePercentileRequest from){
        Child child= childService.getChildById(from.getChildId());
        // exception ekle
        return percentileRepository
                .save(PercentileCalc.builder()
                .height(from.getHeight())
                .weight(from.getWeight())
                .child(child)
                .headCirc(from.getHeadCirc())
                .createdTime(LocalDateTime.now())
                .build());
    }
    public List<PercentileCalc> getAllPercentile() {
        return percentileRepository.findAll();
    }

    public Optional<PercentileCalc> getPercentileById(Long id) {

        return percentileRepository.findById(id)
                .map(e -> new PercentileCalc(
                        e.getId(),
                        e.getHeight(),
                        e.getWeight(),
                        e.getHeadCirc(),
                        e.getCreatedTime(),
                        e.getChild()));
    }
}
