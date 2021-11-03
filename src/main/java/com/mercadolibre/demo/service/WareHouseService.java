package com.mercadolibre.demo.service;

import com.mercadolibre.demo.dto.WareHouseDTO;
import com.mercadolibre.demo.model.WareHouse;
import com.mercadolibre.demo.repository.WareHouseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;


@Service
public class WareHouseService {

    @Autowired
    private WareHouseRepository wareHouseRepository;


    public WareHouse save(WareHouseDTO dto) {
        WareHouse wareHouse = convertWareHouseToDTO(dto);
        return wareHouseRepository.save(wareHouse);
    }

    public List<WareHouse> list() {
        return wareHouseRepository.findAll();
    }

    public Optional<WareHouse> findById(Long id) {
        return wareHouseRepository.findById(id);
    }

    public WareHouse update(WareHouseDTO dto, Long id) throws Exception {
        Optional<WareHouse> existWareHouse = findById(id);
        if (existWareHouse.isPresent()) {
            WareHouse wareHouse= convertWareHouseToDTO(dto);
            wareHouse.setIdWareHouse(id);
            return wareHouseRepository.saveAndFlush(wareHouse);
        } else {
            throw new Exception("Id não cadastrado");
        }
    }

    public void delete(Long id) {
        wareHouseRepository.deleteById(id);
    }

    public WareHouse convertWareHouseToDTO(WareHouseDTO dto) {
        return new WareHouse(dto.getWareHouseName());
    }

}
