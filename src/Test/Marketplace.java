package Test;

import java.util.*;

public class Marketplace {

    private LinkedHashSet<Merchant> merchants = new LinkedHashSet<>();
    private LinkedHashMap<Merchant, TreeSet<Products>> catalog = new LinkedHashMap<>();

    public void registerMerchant(Merchant m) {
        merchants.add(m);
        catalog.putIfAbsent(m, new TreeSet<>());
    }

    public void addProductToMerchant(int merchantId, Products p) {
        for (Merchant m : merchants) {
            if (m.getId() == merchantId) {
                catalog.get(m).add(p);
                return;
            }
        }
    }

    public String listMerchantsWithProducts() {
        StringBuilder sb = new StringBuilder();

        for (Merchant m : merchants) {
            sb.append(m).append(":\n");
            for (Products p : catalog.get(m)) {
                sb.append("   - ").append(p).append("\n");
            }
        }
        return sb.toString();
    }
}
