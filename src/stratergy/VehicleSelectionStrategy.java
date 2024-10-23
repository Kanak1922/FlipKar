package stratergy;

import java.util.List;
import model.Branch;
import enums.VehicleType;

public interface VehicleSelectionStrategy {
    Branch selectBranch(List<Branch> branches, VehicleType vehicleType);
}
