//setting the saved board name
var myHeaders = new Headers();
myHeaders.append("Content-Type", "application/json");



var requestOptions = {
  method: 'GET',
  headers: myHeaders,
  redirect: 'follow'
};

fetch("http://localhost:8080/api/boards/5", requestOptions)
  .then((response) =>{ return response.json()})
  .then((result) => {
    console.log(result)
    let boardName=document.getElementById("boardName")
    boardName.innerText = " Welcome "+result[0].boardName


  })
  .catch(error => console.log('error', error));



//update board name
let boardNav = document.getElementById("setName");
boardNav.addEventListener("submit", (event) => {
    event.preventDefault();
    let name = document.getElementById("boardNameInput").value.trim();

        if (name === "") {
            alert("Please enter a valid name.");
        } else {
    
           
          boardName.innerText = "Welcome " + name
            var myHeaders = new Headers();
            myHeaders.append("Content-Type", "application/json");
            
            var raw = JSON.stringify({
              "boardName": name
            });
            
            var requestOptions = {
              method: 'PUT',
              headers: myHeaders,
              body: raw,
              redirect: 'follow'
            };
            
            fetch("http://localhost:8080/api/boards/5", requestOptions)
              .then(response => response.text())
              .then(result => console.log(result))
              .catch(error => console.log('error', error));

            boardNameInput.value = ""
        }
  
})