package tw.com.fcb.sample.io.iris;

public class RetireAge {
		private Long id;
		private String type;
	    private int voluntary_cnt;
	    private int age_cnt;
	    private int order_cnt;

	    @Override
	    public String toString() {
	        return "RetireAge{" +
	                "id=" + id +
	                ", type='" + type + '\'' +
	                ", voluntary_cnt=" + voluntary_cnt +
	                ", age_cnt=" + age_cnt +
	                ", order_cnt=" + order_cnt +
	                '}';
	    }

	    public Long getId() {
	        return id;
	    }

	    public void setId(Long id) {
	        this.id = id;
	    }

	    public String getType() {
	        return type;
	    }

	    public void setType(String type) {
	        this.type = type;
	    }

	    public int getVoluntary_cnt() {
	        return voluntary_cnt;
	    }

	    public void setVoluntary_cnt(int voluntary_cnt) {
	        this.voluntary_cnt = voluntary_cnt;
	    }

	    public int getAge_cnt() {
	        return age_cnt;
	    }

	    public void setAge_cnt(int age_cnt) {
	        this.age_cnt = age_cnt;
	    }

	    public int getOrder_cnt() {
	        return order_cnt;
	    }

	    public void setOrder_cnt(int order_cnt) {
	        this.order_cnt = order_cnt;
	    }

}
