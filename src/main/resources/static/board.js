//const hostName = window.location.hostname
 const hostName = "localhost"
// Defining async function
async function getapi() {
document.getElementById("boardGrid").innerText=""
    // Storing response
    const response = await fetch( "http://"+hostName+":8080/api/boards");
   
    // Storing data in form of JSON
    var data = await response.json();
    console.log(data);
    if (response) {
      const boardGrid = document.querySelector(".board-grid");
       boardGrid.innerHtml="";
    }
    data.forEach(board => {
      console.log(board);
      fetchBoard(board.board_id,board.boardName)
  })
}
// Calling that async function
getapi();
 





  function fetchBoard(id, name) {
 
    const boardGrid = document.querySelector(".board-grid");

    const boardDiv = document.createElement("div");
    boardDiv.classList.add("board");
   
    
    const boardIdDiv = document.createElement("div");
    boardIdDiv.classList.add("board-id");
    boardIdDiv.textContent = "ID: " + id;

    const boardNameDiv = document.createElement("div");
    boardNameDiv.classList.add("board-name");
    boardNameDiv.textContent = name;

 const ButtonsDiv = document.createElement("div");
  ButtonsDiv.classList.add("buttonDivs");

    const deleteButton = document.createElement("button");
    deleteButton.classList.add("delete-button");
    deleteButton.textContent = "Delete";
    deleteButton.addEventListener('click', function(){
        deleteBoard(id);
    });
   const goToOtherPage = document.createElement("button");
    goToOtherPage.classList.add("goCards-button");
    goToOtherPage.textContent = "Cards";
    goToOtherPage.addEventListener('click', function(){
        goToBoard(id);
    });
    boardDiv.appendChild(boardIdDiv);
    boardDiv.appendChild(boardNameDiv);

    ButtonsDiv.appendChild(deleteButton);
ButtonsDiv.appendChild(goToOtherPage);
boardDiv.appendChild(ButtonsDiv);
    boardGrid.appendChild(boardDiv);
}

// Function to delete a board
 function deleteBoard(boardIdd) {
  const requestOptions = {
    method: 'DELETE',
    redirect: 'follow'
  };
  
  fetch("http://"+hostName+":8080/api/boards/"+boardIdd, requestOptions)
    .then(response => response.text())
    .then(result =>{ getapi();
      console.log(result)
    })
    .catch(error => console.log('error', error));
}
function goToBoard(idd){
    const myObject = {
        id: idd,
      };
  
      localStorage.setItem("myObject", JSON.stringify(myObject));
      window.location.href = "./pages/card.html";
}


createBoard=document.getElementById('createBoardForm')
createBoard.addEventListener('submit', (event) => {
  event.preventDefault();
  var myHeaders = new Headers();
  myHeaders.append("Content-Type", "application/json");

  var raw = JSON.stringify({
    "boardName": document.getElementById('boardTitle').value
  });

  var requestOptions = {
    method: 'POST',
    headers: myHeaders,
    body: raw,
    redirect: 'follow'
  };

  fetch("http://"+hostName+":8080/api/boards",{
                                                   method: 'POST',
                                                   headers: {
                                                       'Content-Type': 'application/json',
                                                   },
                                                   body: JSON.stringify({
                                                       boardName: document.getElementById('boardTitle').value
                                                   })})
    .then(response => response.text())
    .then(result => { getapi();
      console.log(result)
    })
    .catch(error => console.log('error', error))})



