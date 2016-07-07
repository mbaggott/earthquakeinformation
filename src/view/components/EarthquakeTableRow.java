package view.components;

public final class EarthquakeTableRow {

	private final int number;
	private final String id;
	private final String datetime;
	private final double depth;
	private final double magnitude;
	private final double lat;
	private final double lng;
	
	public EarthquakeTableRow(int number, String id, String datetime, double depth, double magnitude, double lat, double lng) {
		this.number = number;
		this.id = id;
		this.datetime = datetime;
		this.depth = depth;
		this.magnitude = magnitude;
		this.lat = lat;
		this.lng = lng;
	}
	
	public Object get(int index) {
		switch(index) {
		case 0:
			return number;
		case 1:
			return id;
		case 2:
			return datetime;
		case 3:
			return depth;
		case 4:
			return magnitude;
		case 5:
			return lat;
		case 6:
			return lng;
		default:
			return "";
		}
		
	}
	
	public int size() {
		return 7;
	}
	
}
