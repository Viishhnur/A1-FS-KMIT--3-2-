class address {
  String location;
  String city;
  int pincode;
  address(String loc, String city, int pincode)  {
    this.location = loc;
    this.city = city;
    this.pincode = pincode;
  }
  String getLocation() { return location; }
}

class employee {
  address home;
  address office;
  employee(address permanent, address work)  {
    home = permanent;
    office = work;
  }
}

class test {
  public static void main(String[] args) {
    address home = new address("Musheerabad", "Hyderabad", 500020);
    address work = new address("Narayanguda", "Hyderabad", 500029);
    employee obj = new employee(home, work);
    System.out.println(obj.home.location + " " + obj.home.city + " " + obj.home.pincode);
    System.out.println(obj.office.getLocation() + " " + obj.office.city + " " + obj.office.pincode);
  }
}
