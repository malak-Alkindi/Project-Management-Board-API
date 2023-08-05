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

//*hide and show create card form *//
const showFormBtn = document.getElementById('showFormBtn');
const closeFormBtn = document.getElementById('closeFormBtn');
const cardpopupForm = document.getElementById('cardpopupForm');

showFormBtn.addEventListener('click', () => {
    cardpopupForm.style.display = 'block';
});


closeFormBtn.addEventListener('click', () => {
    cardpopupForm.style.display = 'none';
});

window.addEventListener('click', (event) => {
    if (event.target === cardpopupForm) {
        cardpopupForm.style.display = 'none';
    }
});

cardpopupForm.querySelector('form').addEventListener('submit', (event) => {
    event.preventDefault();

    var myHeaders = new Headers();
    myHeaders.append("Content-Type", "application/json");
    
    var raw = JSON.stringify({
      "title":  document.getElementById('cardname').value,
      "description": document.getElementById('carddescription').value,
      "section": document.getElementById("status").value
    });
    
    var requestOptions = {
      method: 'POST',
      headers: myHeaders,
      body: raw,
      redirect: 'follow'
    };
    
    fetch("http://localhost:8080/api/boards/1/cards", requestOptions)
      .then(response => response.text())
      .then(result => console.log(result))
      .catch(error => console.log('error', error));

   
 cardpopupForm.style.display = 'none';
});

// Function addCardToHtml(){
  
//   const card = document.createElement('div');
//   card.classList.add('card');

//   const stateBar = document.createElement('div');
//   stateBar.classList.add('state-bar');

//   const cardContent = document.createElement('div');
//   cardContent.classList.add('card-content');

//   const cardIdElement = document.createElement('div');
//   cardIdElement.classList.add('card-id');
//   cardIdElement.innerText = '#' ;

//   const cardTitleElement = document.createElement('h3');
//   cardTitleElement.classList.add('card-title');
//   cardTitleElement.innerText = document.getElementById('cardname').value.trim();

//   const cardDescriptionElement = document.createElement('p');
//   cardDescriptionElement.classList.add('card-description');
 
//   cardDescriptionElement.innerText = document.getElementById('carddescription').value;

//   const actions = document.createElement('div');
//   actions.classList.add('actions');

//   const updateBtn = document.createElement('button');
//   updateBtn.classList.add('update-btn');
//   updateBtn.innerText = 'Update';

//   const deleteBtn = document.createElement('button');
//   deleteBtn.classList.add('delete-btn');
//   deleteBtn.innerText = 'Delete';

//   // Append all elements to the card
//   card.appendChild(stateBar);
//   card.appendChild(cardContent);
//   cardContent.appendChild(cardIdElement);
//   cardContent.appendChild(cardTitleElement);
//   cardContent.appendChild(cardDescriptionElement);
//   card.appendChild(actions);
//   actions.appendChild(updateBtn);
//   actions.appendChild(deleteBtn);
// if(document.getElementById("status").value=='1'){
// document.getElementById('todo').appendChild(card);
// }
// else if(document.getElementById("status").value=='2'){
// document.getElementById('inprogress').appendChild(card);
// }
// else if(document.getElementById("status").value=='3'){
// document.getElementById('done').appendChild(card);
// }
// }