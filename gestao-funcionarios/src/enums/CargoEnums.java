package enums;

import services.CargoStrategy;
import services.GerenteStrategy;
import services.SecretarioStrategy;
import services.VendedorStrategy;

public enum CargoEnums {
    SECRETARIO(new SecretarioStrategy()),
    VENDEDOR(new VendedorStrategy()),
    GERENTE(new GerenteStrategy());

    private final CargoStrategy service;

    CargoEnums(CargoStrategy service) {
        this.service = service;
    }

    public CargoStrategy getService() {
        return service;
    }

    public static CargoEnums buscarPorClasse(Object obj) {
        String nomeClasse = obj.getClass().getSimpleName().toUpperCase();
        try {
            return CargoEnums.valueOf(nomeClasse);
        } catch (IllegalArgumentException e) {
            throw new RuntimeException("Cargo não mapeado: " + nomeClasse);
        }
    }
}