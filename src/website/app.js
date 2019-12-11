//  function onDatabaseChange(snap) {
//      var list_html = "<ul>";
//      var colon = ": ";
//      var space = " ";
//      var data_obj = snap.val();
//     var my_div = document.getElementById("leaderboard_div");
//     do {
//      for (var entry in data_obj) {
//          if (data_obj[entry].userScore > data_obj[entry + 1].userScore) {
//                 let tmp = data_obj[entry];
//                 data_obj[entry] = data_obj[entry + 1];
//                 data_obj[entry + 1] = tmp;
//                 swapped = true;
//           }
//         }
//     } while(swapped)
//      list_html += "</ul>";
//      my_div.innerHTML = list_html;
//  }
    
//     //Sort Elements
//     do {
//         swapped = false;
//         for (var entry in data_obj) {
//             if (data_obj[entry].userScore > data_obj[entry + 1].userScore) {
//                 let tmp = data_obj[entry];
//                 data_obj[entry] = data_obj[entry + 1];
//                 data_obj[entry + 1] = tmp;
//                 swapped = true;
//             }
//         }
//     } while (swapped);
    


 function onDatabaseChange(snap) {
     var list_html = "<ul>";
     var colon = ": ";
     var space = " ";
     var data_obj = snap.val();
    var my_div = document.getElementById("leaderboard_div");
     for (var entry in data_obj) {
         list_html += "<li>" + data_obj[entry].userName + colon + space + data_obj[entry].userScore + "</li>";
     }
     list_html += "</ul>";
     my_div.innerHTML = list_html;
 }

(function(){
    //Initialize Firebase
        const config={
            apiKey:"AIzaSyCOp8f7iPBDQfqUolcH97xgDCgBgFVNZWM",
            authDomain:"quizapp-29372.firebaseapp.com",
            databaseURL:"https://quizapp-29372.firebaseio.com",
            storageBucket:"quizapp-29372.appspot.com"
            //MessagingSender
        };
    firebase.initializeApp(config);
    
//     var userDataRef = firebase.database().ref("user").orderByKey();
//     userDataRef.once("value").then(function(snapshot) {
//     snapshot.forEach(function(childSnapshot) {
//       var key = childSnapshot.key;
//       var childData = childSnapshot.val();              

//       var name_val = childSnapshot.val().Name;
//       var id_val = childSnapshot.val().AssignedID;

//       $("#name").append(name_val);
//       $("#id").append(id_val);

//       });
//     });
    
//     var userDataRef = firebase.database().ref("user").orderByKey();
//     userDataRef.once("value").then
//     (function(snapshot)) {
//      snapshot.forEach(function(childSnapshot){
//         var key = childSnapshot.key;
//         var childData = chil  
//     var ref = firebase.database().ref();

//     ref.on("value", function(snapshot) {
//        console.log(snapshot.val());
//     }, function (error) {
//        console.log("Error: " + error.code);
//     });

     //Get Elements
     const preObject=document.getElementById('user');

     //Create References
     const dbRefObject = firebase.database().ref().child('user');
     //Syn Changes
     
   //Show sorted values in the console: works
     dbRefObject.orderByChild("userScore").on("child_added", snap => {console.log(snap.val());
     });
   //Show sorted values on the page: does not work
     //dbRefObject.orderByChild("userScore").on("child_added", snap => onDatabaseChange(snap));
   //Show unsorted values on the screen: works
     dbRefObject.on('value',snap => onDatabaseChange(snap));
}());
