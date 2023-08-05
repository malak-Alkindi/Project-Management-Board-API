//setting the saved board name----------------------------------

const jsonString = localStorage.getItem("myObject");
const myObject = JSON.parse(jsonString);
const boardiD=myObject.id;
var myHeaders = new Headers();
myHeaders.append("Content-Type", "application/json");


var requestOptions = {
  method: 'GET',
  headers: myHeaders,
  redirect: 'follow'
};

fetch("http://localhost:8080/api/boards/"+boardiD, requestOptions)
  .then((response) =>{ return response.json()})
  .then((result) => {
    console.log(result)
    let boardName=document.getElementById("boardName")
    boardName.innerText = " Welcome "+result[0].boardName


  })
  .catch(error => console.log('error', error));



//getting the caards--------------------------------------------------------
  var requestOptions = {
    method: 'GET',
    redirect: 'follow'
  };
  
  fetch("http://localhost:8080/api/boards/"+boardiD+"/cards", requestOptions)
    .then((response) => {return response.json()})
    .then((result) => {
      result.forEach(cardElment => {
        createHtmlCard(cardElment.cardId,cardElment.title,cardElment.description,cardElment.section)
    }) })
    .catch(error => console.log('error', error));

//update board name----------------------------------------------------
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
            
            fetch("http://localhost:8080/api/boards/"+boardiD, requestOptions)
              .then(response => response.text())
              .then(result =>{ location.reload();
                console.log(result)
              })
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
    
    fetch("http://localhost:8080/api/boards/"+boardiD+"/cards", requestOptions)
      .then(response => response.text())
      .then(result =>{ location.reload();
        console.log(result)
      })
      .catch(error => console.log('error', error));

   
 cardpopupForm.style.display = 'none';
});

function createHtmlCard(id,name,description,section){
  
  const card = document.createElement('div');
  card.classList.add('card');

  const stateBar = document.createElement('div');
  stateBar.classList.add('state-bar');

  const cardContent = document.createElement('div');
  cardContent.classList.add('card-content');

  const cardIdElement = document.createElement('div');
  cardIdElement.classList.add('card-id');
  cardIdElement.innerText = '#'+id ;

  const cardTitleElement = document.createElement('h3');
  cardTitleElement.classList.add('card-title');
  cardTitleElement.innerText = name;

  const cardDescriptionElement = document.createElement('p');
  cardDescriptionElement.classList.add('card-description');
 
  cardDescriptionElement.innerText =description;

  const actions = document.createElement('div');
  actions.classList.add('actions');

  const updateBtn = document.createElement('button');
  updateBtn.classList.add('update-btn');
  updateBtn.innerText = 'Update';
  updateBtn.addEventListener('click', function(){
   Update(id,name,description,section)
  });
  const deleteBtn = document.createElement('button');
  deleteBtn.classList.add('delete-btn');
  deleteBtn.innerText = 'Delete';
    deleteBtn.innerText = 'Delete';
    deleteBtn.addEventListener('click', function(){
      deleteCard(id);
  });
  // Append all elements to the card
  card.appendChild(stateBar);
  card.appendChild(cardContent);
  cardContent.appendChild(cardIdElement);
  cardContent.appendChild(cardTitleElement);
  cardContent.appendChild(cardDescriptionElement);
  card.appendChild(actions);
  actions.appendChild(updateBtn);
  actions.appendChild(deleteBtn);
if(section=='1'){
document.getElementById('todo').appendChild(card);
}
else if(section=='2'){
document.getElementById('inprogress').appendChild(card);
}
else if(section=='3'){
document.getElementById('done').appendChild(card);
}
}




const ushowFormBtn = document.getElementById('ushowFormBtn');
const ucloseFormBtn = document.getElementById('ucloseFormBtn');
const ucardpopupForm = document.getElementById('ucardpopupForm');

ucloseFormBtn.addEventListener('click', () => {
  ucardpopupForm.style.display = 'none';
});



window.addEventListener('click', (event) => {
    if (event.target === ucardpopupForm) {
      ucardpopupForm.style.display = 'none';
    }
    
});
function Update(id,title,description,section){

  ucardpopupForm.style.display = 'block';

 
 document.getElementById('ucardname').value=title;
  document.getElementById('ucarddescription').value=description;
 document.getElementById('ustatus').value=section;

 ucardpopupForm.querySelector('form').addEventListener('submit', (event) => {
  event.preventDefault();
 
 var myHeaders = new Headers();
 myHeaders.append("Content-Type", "application/json");

 
  var raw = JSON.stringify({
    "title": document.getElementById('ucardname').value,
    "description":document.getElementById('ucarddescription').value,
    "section": document.getElementById('ustatus').value
  });
  

  var requestOptions = {
    method: 'PUT',
    headers: myHeaders,
    body: raw,
    redirect: 'follow'
  };
  
  fetch("http://localhost:8080/api/boards/"+boardiD+"/cards/"+id, requestOptions)
  .then(response => response.text())
  .then(result =>{ 
    ucardpopupForm.style.display = 'none';
    location.reload();
    console.log(result)
  } )
  .catch(error => console.log('error', error));
 })

 }


a=function deleteCard(id)
{
var requestOptions = {
  method: 'DELETE',
  redirect: 'follow'
};

fetch("http://localhost:8080/api/boards/"+boardiD+"/cards/"+id, requestOptions)
  .then(response => response.text())
  .then(result => { 
    location.reload();
    console.log(result)
})
  .catch(error => console.log('error', error));
}



 