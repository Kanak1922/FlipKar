package stratergy;

import java.util.Comparator;
import java.util.List;
import model.Branch;
import enums.VehicleType;

public class LowestPriceStrategy implements VehicleSelectionStrategy {

    @Override
    public Branch selectBranch(List<Branch> branches, VehicleType vehicleType) {

        return branches.stream()
                .filter(branch -> branch.getVehicle(vehicleType) != null && branch.getVehicle(vehicleType).isAvailable())
                .min(Comparator.comparingDouble(branch -> branch.getVehicle(vehicleType).getPricePerHour()))
                .orElse(null); // Return null if no available branch
    }
}

