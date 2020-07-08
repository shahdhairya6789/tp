package com.controller;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.bean.Customer;
import com.bean.Employee;
import com.bean.Employee1;
import com.bean.Oper;
import com.dao.EmployeeDao;

@Controller
public class EmployeeController {

	@Autowired
	EmployeeDao employeedao;
	
//	@RequestMapping(value="/EmployeeController",method=RequestMethod.POST)
//	public String insertEmployee(Employee employee)
//	{
//		
//		return "Home";
//	}
	
//	public String insertValues1(Employee employee,Model model)
//	{
//		model.addAttribute("fn", employee.getFirstName());
//		System.out.println("Inserted Employee1");
//		return "Home";
//	}
//	@RequestMapping(value="/insertValues",method=RequestMethod.POST)
//	public String insertValues1(Employee employee,Model model)
//	{
//		System.out.println("insertValues");
//		model.addAttribute("fn", employee.getFirstName());
//		employeedao.insert(employee);
//		System.out.println("Inserted Employee1");
//		System.out.println(employeedao);
//		return "Home";
//	}
//	
	@RequestMapping(value="/insertValues",method=RequestMethod.POST)
	public String insertValues(Employee employee,Model model)
	{
		System.out.println(employee);
		System.out.println("insertValues");
		//model.addAttribute("fn", employee.getFirstName());
		employeedao.insert(employee);
		System.out.println("Inserted Employee");
		System.out.println(employeedao);
		return "redirect:/listUsers";
	}
	
	@RequestMapping(value="/listUsers",method=RequestMethod.GET)
	public String listUsers(Model model)
	{
		model.addAttribute("list",employeedao.listUsers());
		return "Home";
	}
	
	@RequestMapping(value="/deleteUser/{id}",method=RequestMethod.GET)
	public String deleteUser(@PathVariable int id)
	{
		employeedao.deleteUser(id);
		return "redirect:/listUsers";
	}
	
	@RequestMapping(value = "/editUser/{id}", method = RequestMethod.GET)
	public String editUser(@PathVariable int id,Model model)
	{
		Employee employee=employeedao.getUser(id);
		model.addAttribute("command", employee);
		return "editUser";
	}
	
	@RequestMapping(value="/updateUser",method=RequestMethod.POST)
	public String updateUser(@ModelAttribute Employee employee,Model model)
	{
		System.out.println("Update");
		employeedao.updateUser(employee);
		return "redirect:/listUsers";
	}
	
	@RequestMapping(value="/abc",method=RequestMethod.GET)  
    public String saveCustomer(Model model)  
    {  
        model.addAttribute("customer", new Customer());  
        return "CutSave";  
    }  
	
	
	@RequestMapping(value="/CutSave",method=RequestMethod.POST)
	public String saveCustomerAction(@Valid Customer customer,BindingResult bindingResult,Model model)
	{
		System.out.println(bindingResult.hasErrors());
		if(bindingResult.hasErrors())
		{
			System.out.println("er");
			return "CutSave";
		}
		System.out.println("success");
		model.addAttribute("customer", customer);
		return "Success";
	}
	
	
	@RequestMapping(value="/Operation",method=RequestMethod.GET)
	public String Operation(Oper oper,Model model)
	{
		String num1=oper.getNum1();
		String num2=oper.getNum2();
		String op=oper.getOp();
		System.out.println(num1);
		if(op.equals("add"))
		{
			int ab=Integer.parseInt(num1)+Integer.parseInt(num2);
			model.addAttribute("a", "Addition is "+ab);
		}
		else
		{
			int a=Integer.parseInt(num1)-Integer.parseInt(num2);
			model.addAttribute("a", "Substraction is "+a);
		}
		return "Addition";
	}
}
