package com.mitocode.service.impl;

import com.mitocode.dto.IProcedureDTO;
import com.mitocode.dto.ProcedureDTO;
import com.mitocode.model.Sale;
import com.mitocode.model.SaleDetail;
import com.mitocode.repo.ISaleRepo;
import com.mitocode.repo.IGenericRepo;
import com.mitocode.service.ISaleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static java.util.stream.Collectors.groupingBy;
import static java.util.stream.Collectors.summingDouble;

@Service
public class SaleServiceImpl extends CRUDImpl<Sale,Integer> implements ISaleService{

    @Autowired
    private ISaleRepo repo;

    @Override
    protected IGenericRepo<Sale, Integer> getRepo() {
        return this.repo;
    }

    @Override
    public List<ProcedureDTO> callProcedure(){
        List<ProcedureDTO> list = new ArrayList<>();
        repo.callProcedure().forEach(e -> {
            ProcedureDTO dto = new ProcedureDTO();
            dto.setQuantityFn((Integer) e[0]);
            dto.setDateTimeFn((String) e[1]);
            list.add(dto);
        });
        return list;
    }

    @Override
    public List<ProcedureDTO> callProcedure2() {
        return repo.callProcedure2();
    }

    @Transactional
    @Override
    public List<IProcedureDTO> callProcedure3() {
        return repo.callProcedure3();
    }

    @Transactional
    @Override
    public List<IProcedureDTO> callProcedure4(Integer id) {
        return repo.callProcedure4(id);
    }

    @Override
    public Sale getMostExpensive() {
        return repo.findAll()
                .stream()
                .max(Comparator.comparing(e -> e.getTotal()))
                .orElse(new Sale());
    }

    @Override
    public String getBestSellerPerson() {
        Map<String, Double> byUser = repo.findAll().stream()
                                    .collect(groupingBy(s -> s.getUser().getUsername(), summingDouble(e -> e.getTotal())));
        String user = Collections.max(byUser.entrySet(), Comparator.comparingDouble(Map.Entry::getValue)).getKey();
        return user;
    }

    @Override
    public Map<String, Long> getSalesCountBySeller() {
        return repo.findAll().stream()
                .collect(Collectors.groupingBy(s -> s.getUser().getUsername(), Collectors.counting()));
    }

    @Override
    public Map<String, Double> getMostSellerProduct() {
        Stream<List<SaleDetail>> stream = repo.findAll().stream().map(e -> e.getDetails()); //Stream<Sale>
        Stream<SaleDetail> saleDetailStream = stream.flatMap(list -> list.stream());
        Map<String, Double> byProduct = saleDetailStream
                .collect(Collectors.groupingBy(d -> d.getProduct().getName(), summingDouble(e -> e.getQuantity())));
        return byProduct.entrySet()
                .stream()
                .sorted(Map.Entry.comparingByValue(Comparator.reverseOrder()))
                .collect(Collectors.toMap(
                        Map.Entry::getKey,
                        Map.Entry::getValue,
                        (oldValue, newValue) -> oldValue, LinkedHashMap::new)
                );
    }
}
