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
//    @PostMapping(value="results")  // <<<which is better? - PostMapping does not work for Bonus 3 links
    public String displaySearchResults(Model model, @RequestParam(defaultValue = "all") String searchType, @RequestParam  String searchTerm){

        ArrayList<Job> jobs;
         if (searchType.toLowerCase().equals("all") && (searchTerm.toLowerCase().equals("all") || searchTerm.toLowerCase().equals(" ") || searchTerm.toLowerCase().equals("") || searchTerm.toLowerCase().equals(null))) {  //
             jobs = JobData.findAll();
             model.addAttribute("title","All Jobs");
//         } else if (searchType.toLowerCase().equals("all") ){
         } if (searchType.toLowerCase().equals("all") ){
             jobs = JobData.findByColumnAndValue(searchType, searchTerm);
             model.addAttribute("title",searchType + " fields searched for search term: '" + searchTerm + "'");
         } else {
             jobs = JobData.findByColumnAndValue(searchType, searchTerm);
//             model.addAttribute("title","Jobs with " + ListController.columnChoices.get(searchType) + ": '" + searchTerm + "'");
            model.addAttribute("title","Jobs with " + searchType + ": '" + searchTerm + "'");
        }
        System.out.println("searchType: " + searchType + ", searchTerm: " + searchTerm);
        model.addAttribute("columns", columnChoices);
        model.addAttribute("jobs", jobs);
        return "search";
    }

}
