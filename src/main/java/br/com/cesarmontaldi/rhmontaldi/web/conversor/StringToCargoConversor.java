package br.com.cesarmontaldi.rhmontaldi.web.conversor;

import br.com.cesarmontaldi.rhmontaldi.domain.Cargo;
import br.com.cesarmontaldi.rhmontaldi.service.CargoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class StringToCargoConversor implements Converter<String, Cargo> {

    @Autowired
    private CargoService cargoService;

    @Override
    public Cargo convert(String text) {
        if (text.isEmpty()) {
            return null;
        }
        Long id = Long.valueOf(text);
        return cargoService.buscarPorId(id);

    }
}
