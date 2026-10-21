
package com.bhanu.LostandFoundItem.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.bhanu.LostandFoundItem.model.LostFoundItem;
import com.bhanu.LostandFoundItem.repo.LostFoundRepository;
@CrossOrigin(origins = "http://localhost:5173")
@RestController
@RequestMapping("/LostFound")
public class LostFoundController {

    @Autowired
    private LostFoundRepository lostfoundRepo;

    // 1. create item
    @PostMapping("/create")
    public LostFoundItem createData(@RequestBody LostFoundItem lostfound) {
        return lostfoundRepo.save(lostfound);
    }
    
    
    //2.get all items
    @GetMapping("/getAllLostFoundItem")
  List<LostFoundItem>  getAllUserDetails(){
	  return lostfoundRepo.findAll();
    	
    }
    
    
    //3.get single item by id
    @GetMapping("/getAllLostFoundItem/{id}")
    LostFoundItem getUserDetailsById(@PathVariable Long id) {
    	return lostfoundRepo.findById(id).get();
    }
    
    //4.update item by id
    
    @PutMapping("/update/{id}")
    LostFoundItem upDateLostFoundItemDetails(@RequestBody LostFoundItem lft,@PathVariable Long id) {
    	LostFoundItem existingItem= lostfoundRepo.findById(id).orElseThrow(()-> new RuntimeException(
    			"Lost and Found Item not Found with ID:"+id));
    	
    	existingItem.setItemName(lft.getItemName());
    	existingItem.setDescription(lft.getDescription());
    	existingItem.setItemType(lft.getItemType());
    	existingItem.setLocation(lft.getLocation());
    	existingItem.setReportedBy(lft.getReportedBy());
    	existingItem.setContactInfo(lft.getContactInfo());
    	existingItem.setReportedDate(lft.getReportedDate());
    	existingItem.setStatus(lft.getStatus());
    	
    	return lostfoundRepo.save(existingItem);
    }
    
    
    @DeleteMapping("/delete/{id}")
    String deletLostFoundItemInfo(@PathVariable Long id) {
    	 if (!lostfoundRepo.existsById(id)) {
             throw new ResponseStatusException(
                     HttpStatus.NOT_FOUND,
                     "Lost and Found item not found with ID: " + id);
         }
    	lostfoundRepo.deleteById(id);
    	
    		return "Delete Successfully";
    }
    
    
//search by name..
@GetMapping("/search")
public List<LostFoundItem> searchByItemName(
        @RequestParam String itemName) {

    return lostfoundRepo
            .findByItemNameContainingIgnoreCase(itemName);
}

  

// filtering
@GetMapping("/filter")
List<LostFoundItem> filterByItemType(
		@RequestParam LostFoundItem.ItemType itemType){
	return lostfoundRepo.findByItemType(itemType);
}
}
