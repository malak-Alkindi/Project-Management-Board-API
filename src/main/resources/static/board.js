// const jsonString = localStorage.getItem("myObject");
// const myObject = JSON.parse(jsonString);
// console.log(myObject.key1);


var requestOptions = {
    method: 'GET',
    redirect: 'follow'
  };
fetch("http://localhost:8080/api/boards", requestOptions)
    .then((response) => {return response.json()})
    .then((result) => {
      result.forEach(board => {
        createBoard(board.board_id,board.boardName)
    })
  })
  .catch(error => console.log('error', error));



  function createBoard(id, name) {
    const boardGrid = document.querySelector(".board-grid");

    const boardDiv = document.createElement("div");
    boardDiv.classList.add("board");
    boardDiv.id = id;

    const boardIdDiv = document.createElement("div");
    boardIdDiv.classList.add("board-id");
    boardIdDiv.textContent = "ID: " + id;

    const boardNameDiv = document.createElement("div");
    boardNameDiv.classList.add("board-name");
    boardNameDiv.textContent = name;

    const deleteButton = document.createElement("button");
    deleteButton.classList.add("delete-button");
    deleteButton.textContent = "Delete";
    deleteButton.onclick = function () {
        deleteBoard(id);
    };

    boardDiv.appendChild(boardIdDiv);
    boardDiv.appendChild(boardNameDiv);
    boardDiv.appendChild(deleteButton);

    boardGrid.appendChild(boardDiv);
}

// Function to delete a board
function deleteBoard(boardId) {
    const boardToRemove = document.getElementById(boardId);
    if (boardToRemove) {
        boardToRemove.remove();
    }
}

