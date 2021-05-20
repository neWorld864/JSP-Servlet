package com.kh.practice;

import java.io.IOException;
import java.util.Arrays;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class PizzaServlet extends HttpServlet{
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.setCharacterEncoding("UTF-8");
		String pizza = request.getParameter("pizza");
		String[] toppings = request.getParameterValues("topping");
		String[] sides = request.getParameterValues("side");
		
		int total = 0;
		String topping = "";
		String side = "";
		
		switch(pizza) {
		case "치즈피자": total += 5000; break;
		case "콤비네이션피자": total += 6000; break;
		case "포테이토피자": total += 7000; break;
		case "고구마피자": total += 7000; break;
		case "불고기피자": total += 8000; break;
		}
		
		if(toppings != null) {
			for(int i = 0; i < toppings.length; i++) {
				
				if(i == 0) {
					topping += toppings[i];
				} else {
					topping += ", " + toppings[i];
				}
				
				switch(toppings[i]) {
				case "고구마무스": total += 1000; break;
				case "콘크림무스": total += 1500; break;
				case "파인애플토핑": total += 2000; break;
				case "치즈토핑": total += 2000; break;
				case "치즈크러스트": total += 2000; break;
				case "치즈바이트": total += 3000; break;
				}
			}
		}
		
		if(sides != null) {
			for(int i = 0; i < sides.length; i++) {
				
				if(i == 0) {
					side += sides[i];
				} else {
					side += ", " + sides[i];
				}
				
				switch(sides[i]) {
				case "오븐구이통닭": total += 9000; break;
				case "치킨스틱&윙": total += 4900; break;
				case "치즈오븐스파게티": total += 4000; break;
				case "새우링&웨지감자": total += 3500; break;
				case "갈릭포테이토": total += 3000; break;
				case "콜라": total += 1500; break;
				case "사이다": total += 1500; break;
				case "갈릭소스": total += 500; break;
				case "피클": total += 300; break;
				case "핫소스": total += 100; break;
				case "파마산 치즈가루": total += 100; break;
				}
			}
		}
		
		request.setAttribute("pizza", pizza);
		request.setAttribute("topping", topping);
		request.setAttribute("side", side);
		request.setAttribute("total", total);
		
		RequestDispatcher view = request.getRequestDispatcher("jsp/04_pizzaEnd.jsp");
		view.forward(request, response);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doGet(req, resp);
	}
}