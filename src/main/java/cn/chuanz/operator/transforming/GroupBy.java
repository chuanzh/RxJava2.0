package cn.chuanz.operator.transforming;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import io.reactivex.Observable;
import io.reactivex.functions.Function;

public class GroupBy {
	
	private Logger logger = LoggerFactory.getLogger(GroupBy.class);

	public static void main(String[] args) {
		new GroupBy().groupBy();
	}
	
	private void groupBy() {
		Observable.just(1, 2, 3, 4, 5)
			      //.groupBy(i -> i%2==0?"even":"odd")
				  .groupBy(new Function<Integer, String>() {
					@Override
					public String apply(Integer t) throws Exception {
						return t % 2 == 0 ? "even" : "odd";
					}})
				  .subscribe(g -> g.subscribe(i -> logger.info(g.getKey()+":"+i)));
	}
	
	private void groupBy2() {
		Map<String, List<Data>> newData = new HashMap<String, List<Data>>();
		Observable.fromIterable(createDatas())
		          .groupBy(data -> data.getTime().substring(0, 10))
		          .subscribe(g -> g.subscribe(data -> logger.info(g.getKey()+":"+data)));
	}
	
	private List<Data> createDatas() {
		List<Data> datas = new ArrayList<Data>();
		Data d = new Data();
		d.setTime("2017-11-30 10:00:00");
		d.setName("data1");
		datas.add(d);
		
		d = new Data();
		d.setTime("2017-11-31 10:00:00");
		d.setName("data2");
		datas.add(d);
		
		d = new Data();
		d.setTime("2017-11-32 10:00:00");
		d.setName("data3");
		datas.add(d);
		
		d = new Data();
		d.setTime("2017-11-30 10:00:00");
		d.setName("data4");
		datas.add(d);
		
		d = new Data();
		d.setTime("2017-11-31 10:00:00");
		d.setName("data5");
		datas.add(d);
		
		return datas;
	}
	
	
	static class Data {
		private String time;
		private String name;
		public String getTime() {
			return time;
		}
		public void setTime(String time) {
			this.time = time;
		}
		public String getName() {
			return name;
		}
		public void setName(String name) {
			this.name = name;
		}
		
		public String toString() {
			return "{"+time+","+name+"}";
		}
		
	}
	
}
