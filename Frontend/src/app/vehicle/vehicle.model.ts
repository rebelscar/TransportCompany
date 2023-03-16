import { VehicleDetail } from "./vehicle-detail.model";


export class Vehicle {
  id!: number;
  vehicleDetail!: VehicleDetail;

  constructor() {
    if (this.vehicleDetail == null) {
      this.vehicleDetail = new VehicleDetail();
    }
  }
}
