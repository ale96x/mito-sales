package com.mitocode.config;

import com.mitocode.dto.ProductDTO;
import com.mitocode.model.Product;
import org.hibernate.collection.spi.PersistentCollection;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeMap;
import org.modelmapper.spi.DestinationSetter;
import org.modelmapper.spi.SourceGetter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MapperConfig {
    @Bean("categoryMapper") //Identificador para utilizar despues qualifier
    public ModelMapper categotyMapper(){
        return new ModelMapper();
    }

    @Bean("productMapper")
    public ModelMapper productMapper(){
        ModelMapper mapper = new ModelMapper();
        /*TypeMap<ProductDTO, Product> typeMap = mapper.createTypeMap(ProductDTO.class, Product.class); //Mapeo de Tipos
        typeMap.addMapping(ProductDTO::getIdCategory,(dest, v) -> dest.getCategory().setIdCategory((Integer) v));*/
        return mapper;
    }

    @Bean("roleMapper")
    public ModelMapper roleMapper(){
        ModelMapper mapper = new ModelMapper();
        return mapper;
    }

    @Bean("clientMapper") //Identificador para utilizar despues qualifier
    public ModelMapper clientMapper(){
        return new ModelMapper();
    }

    @Bean("providerMapper") //Identificador para utilizar despues qualifier
    public ModelMapper providerMapper(){
        return new ModelMapper();
    }

    @Bean("userMapper") //Identificador para utilizar despues qualifier
    public ModelMapper userMapper(){
        return new ModelMapper();
    }

    @Bean("saleMapper") //Identificador para utilizar despues qualifier
    public ModelMapper saleMapper(){
        ModelMapper mapper = new ModelMapper();
        //mapper.getConfiguration().setPropertyCondition(context -> !(context.getSource() instanceof PersistentCollection));  Sirve para cuando se usa FetchType.LAZY
        return new ModelMapper();
    }
}
