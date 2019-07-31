package management.service;

public class Publisher {
	private String publisher;
	private String address;
	private String phone;
	private String url;

	public Publisher() {
	}

	public Publisher(String publisher, String address, String phone, String url) {
		super();
		this.publisher = publisher;
		this.address = address;
		this.phone = phone;
		this.url = url;
	}

	public String getPublisher() {
		return publisher;
	}

	public void setPublisher(String publisher) {
		this.publisher = publisher;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	@Override
	public String toString() {
		return "Publisher [publisher=" + publisher + ", address=" + address + ", phone=" + phone + ", url=" + url + "]";
	}

}
