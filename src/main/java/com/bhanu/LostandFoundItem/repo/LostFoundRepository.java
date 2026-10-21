package com.bhanu.LostandFoundItem.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bhanu.LostandFoundItem.model.LostFoundItem;

@Repository
public interface LostFoundRepository extends JpaRepository<LostFoundItem, Long>{
	
    // Search items by name
    List<LostFoundItem> findByItemNameContainingIgnoreCase(
            String itemName);
    
    List<LostFoundItem> findByItemType(LostFoundItem.ItemType itemType);

}
