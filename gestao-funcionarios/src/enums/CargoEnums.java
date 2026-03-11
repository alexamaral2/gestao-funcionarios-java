package enums;

import services.CargoService;
import services.GerenteService;
import services.SecretarioService;
import services.VendedorService;

public enum CargoEnums {
    SECRETARIO(new SecretarioService()),
    VENDEDOR(new VendedorService()),
    GERENTE(new GerenteService());

    private final CargoService service;

    CargoEnums(CargoService service) {
        this.service = service;
    }

    public CargoService getService() {
        return service;
    }

    public static CargoEnums buscarPorClasse(Object obj) {
        String nomeClasse = obj.getClass().getSimpleName().toUpperCase();
        try {
            return CargoEnums.valueOf(nomeClasse);
        } catch (IllegalArgumentException e) {
            throw new RuntimeException("Cargo não mapeado no Enum: " + nomeClasse);
        }
    }
}