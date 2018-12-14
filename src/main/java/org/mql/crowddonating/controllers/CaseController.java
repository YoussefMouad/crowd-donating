package org.mql.crowddonating.controllers;

import org.mql.crowddonating.business.implementations.AssociationBusiness;
import org.mql.crowddonating.models.Case;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;

@Controller
public class CaseController {
    @Autowired
    private AssociationBusiness associationBusiness;
    
    @GetMapping("/cases")
    public String cases(Model model) {
    	model.addAttribute("cases", associationBusiness.getAllCases());
        return "cases/cases";
    }
    
    @GetMapping("/cases/add")
    public String add(Model model) {
    	model.addAttribute("aCase", new Case());
    	return "cases/addcase";
    }
    
    @GetMapping("/cases/{id}")
    public String showCase(@PathVariable long id, Model model) {
    	model.addAttribute("aCase", associationBusiness.getById(id));
    	return "cases/details";
    }
    
    @DeleteMapping("/cases/{id}")
    public String delete(@PathVariable long id, Model model) {
    	associationBusiness.deleteCase(id);
    	return "cases/cases";
    }
    
    @PostMapping("/cases")
    public String processAdd(Model model, Case aCase) {
    	System.out.println(aCase);
    	aCase = associationBusiness.addCase(aCase);
    	model.addAttribute("aCase", aCase);
    	return "redirect:cases/"+aCase.getId();
    }
    
    @GetMapping("/cases/edit/{id}")
	public String edit(Model model,@PathVariable("id")long id) {
		
      	Case eCase=associationBusiness.getById(id);
		model.addAttribute("eCase",eCase);
		return "cases/editCase";
	}

    @PutMapping("/cases")
    public String processEdit(Model model, Case aCase) {
    	System.out.println(aCase);
    	aCase = associationBusiness.updateCase(aCase);
    	return "redirect:cases/"+aCase.getId();
    }
    

}
