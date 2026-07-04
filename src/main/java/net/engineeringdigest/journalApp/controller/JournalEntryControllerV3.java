//package net.engineeringdigest.journalApp.controller;
//
//import net.engineeringdigest.journalApp.entity.JournalEntry;
//import net.engineeringdigest.journalApp.entity.User;
//import net.engineeringdigest.journalApp.service.JournalEntryService;
//import net.engineeringdigest.journalApp.service.UserService;
//import org.bson.types.ObjectId;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.*;
//
//import java.util.List;
//import java.util.Optional;
//
//@RestController
//@RequestMapping("/journal")
//public class JournalEntryControllerV3 {
//
//    @Autowired
//    private JournalEntryService journalEntryService;
//
//    @Autowired
//    private UserService userService;
//
//    @GetMapping("/{userName}")
//    public ResponseEntity<?> getAllJournalEntriesOfUser(@PathVariable String userName) {
//        User byUserName = userService.findByUserName(userName);
//        List<JournalEntry> all = byUserName.getJournalEntries();
//
//        if(all != null && !all.isEmpty()){
//            return new ResponseEntity<>(all,HttpStatus.OK);
//        }
//
//        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
//    }
//
//    @PostMapping("{userName}")
//    public ResponseEntity<JournalEntry> createEntry(@RequestBody JournalEntry myEntry, @PathVariable String userName) {
//        try {
//
////            User byUserName = userService.findByUserName(userName);
////            myEntry.setDate(LocalDateTime.now());
//            journalEntryService.saveEntry(myEntry, userName);
//            return new ResponseEntity<>(myEntry, HttpStatus.CREATED);
//        }
//
//        catch (Exception e){
//            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
//        }
//    }
//
//    @GetMapping("id/{myId}")
//    public ResponseEntity<JournalEntry> getJournalEntryById(@PathVariable ObjectId myId) {
//
//        Optional<JournalEntry> journalEntry = journalEntryService.findById(myId);
//
//        if(journalEntry.isPresent()){
//            return new ResponseEntity<>(journalEntry.get(), HttpStatus.OK);
//        }
//        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
//    }
//
//    @DeleteMapping("id/{myId}/{userName}")
//    public ResponseEntity<?> deleteEntryById(@PathVariable ObjectId myId, @PathVariable String userName) {
//
//        journalEntryService.deleteById(myId, userName);
//        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
//    }
//
//    @PutMapping("id/{id}/{userName}")
//    public ResponseEntity<?> updateJournalById(
//            @PathVariable ObjectId id,
//            @RequestBody JournalEntry newEntry,
//            @PathVariable String userName)
//    {
//
//        JournalEntry oldEntry = journalEntryService.findById(id).orElse(null);
//
//        if(oldEntry != null){
//            oldEntry.setTitle(newEntry.getTitle() != null && !newEntry.getTitle().isEmpty() ? newEntry.getTitle() : oldEntry.getTitle());
//            oldEntry.setContent(newEntry.getContent() != null && !newEntry.getContent().isEmpty() ? newEntry.getContent() : oldEntry.getContent());
//            journalEntryService.saveEntry(oldEntry);
//            return new ResponseEntity<>(oldEntry,HttpStatus.OK);
//        }
//       return new ResponseEntity<>(HttpStatus.NOT_FOUND);
//
//    }
//}
