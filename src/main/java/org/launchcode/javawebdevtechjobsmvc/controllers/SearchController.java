package org.launchcode.javawebdevtechjobsmvc.controllers;

import org.launchcode.javawebdevtechjobsmvc.models.Job;
import org.launchcode.javawebdevtechjobsmvc.models.JobData;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.lang.reflect.Array;
import java.util.ArrayList;

import static org.launchcode.javawebdevtechjobsmvc.controllers.ListController.columnChoices;

/**
 * Created by LaunchCode
 */
@Controller
@RequestMapping("search")
public class SearchController {

    @RequestMapping(value = "")
    public String search(Model model) {
        model.addAttribute("columns", columnChoices);
        return "search";
    }

    // TODO #3 - Create a handler to process a search request and render the updated search view.

    @RequestMapping(value="results")
//    @PostMapping(value="results")  // <<<which is better?
    public String displaySearchResults(Model model, @RequestParam String searchType, @RequestParam  String searchTerm){

        ArrayList<Job> jobs;
         if (searchType.toLowerCase().equals("all")) {  //
             jobs = JobData.findAll();
             model.addAttribute("title","All Jobs"); // <<<<<< ******** what is wrong with this? *******
         } else {
             jobs = JobData.findByColumnAndValue(searchType, searchTerm); // <<<<****** check if this is correct! *******
             model.addAttribute("title", "Jobs with " + ListController.columnChoices.get(searchType) + ": " + searchTerm);
         }
        System.out.println("searchType: " + searchType + ", searchTerm: " + searchTerm);
        model.addAttribute("jobs", jobs);
        return "search";
    }
}
