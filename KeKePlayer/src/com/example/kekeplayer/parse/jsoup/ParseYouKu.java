package com.example.kekeplayer.parse.jsoup;

import java.util.ArrayList;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.example.kekeplayer.type.MovieType;

public class ParseYouKu {

	public List<MovieType> ParserYouKu(String url){
		List<MovieType> listItems = new ArrayList<MovieType>();
		Document doc = null;
		try {
			doc =  Jsoup.connect(url).get();
			Elements uls = doc.select("ul.p");
			for (Element ul : uls) {
				MovieType type = new MovieType();
				Elements lis = ul.getElementsByTag("li");
				if (lis != null) {
					int count = lis.size();
					for (int i=0;i<count;i++) {
						switch (i) {
						case 0:
							Elements a = lis.get(i).getElementsByTag("a");
							if (a != null) {
								type.setMovie_name(a.attr("title"));
								type.setMovie_url(a.attr("href"));
							}
							break;
						case 1:
//							Elements img = lis.get(i).getElementsByTag("img");
//							if (img != null) {
//								type.setMovie_img(img.attr("src"));
//							}
							break;
						case 2:
//							Elements span = lis.get(i).getElementsByTag("span");
//							if (span != null) {
//								type.setMovie_type(span.text());
//							}
							break;
						}
					}
				}
				listItems.add(type);
			}
		} catch (Exception e) {
			
	}
		return listItems;
	}
}
