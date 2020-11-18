import {
    allCampuses
} from "./sampleAllCampusJson.js"
import {
    displayHomeView
} from "./displayHomeView.js"
const displaySingleCampus = function (campus) {
    const mainContent = document.querySelector(".main-content")
    clearChildren(mainContent);
    const campusLibraryElement = document.createElement("section");
    campusLibraryElement.classList.add("campus-library");
    mainContent.appendChild(campusLibraryElement);

    const libraryHeader = document.createElement("header");

    const libraryLocationElement = document.createElement("h2");
    libraryLocationElement.innerText = campus.location;
    libraryLocationElement.classList.add("campus-library-header__location")

    const libraryTechStack = document.createElement("h3");
    libraryTechStack.innerText = campus.techStack;
    libraryTechStack.classList.add("campus-library-header__tech-stack")

    libraryHeader.appendChild(libraryLocationElement);
    libraryHeader.appendChild(libraryTechStack);
    campusLibraryElement.appendChild(libraryHeader);

    const booksElement = document.createElement("section");
    booksElement.classList.add("campus-books");
    campusLibraryElement.appendChild(booksElement);

    campus.books.forEach(book => {
        const bookTitle = document.createElement("h3");
        bookTitle.classList.add("book-title");
        bookTitle.innerText = book.title;
        booksElement.appendChild(bookTitle);
        bookTitle.addEventListener('click', () => alert(`This book's summary: ${book.summary}`))
    });

    const newBookForm = document.createElement("form");
    newBookForm.innerHTML = `
        <input class="title-input" placeholder="Title" type="text">
        <input class="summary-input" placeholder="Summary" type="textbox">
        <button class="submit-new-book">Submit New Book</button>
    `
    newBookForm.querySelector(".submit-new-book").addEventListener('click', (clickEvent) => {
        clickEvent.preventDefault();
        clearChildren(mainContent);
        const bookJson = {
            "title": newBookForm.querySelector(".title-input").value,
            "summary": newBookForm.querySelector(".summary-input").value
        }
        fetch("http://localhost:8080/api/campuses/1/books", {
                method: 'PATCH',
                headers: {
                    'Content-Type': 'application/json'
                },
                body: JSON.stringify(bookJson)
            })
            .then(response => response.json())
            .then(campus => displaySingleCampus(campus))
            .catch(err => console.error(err))
    })
    campusLibraryElement.appendChild(newBookForm);

    const backToAllCampuses = document.createElement("a");
    backToAllCampuses.classList.add("back-navigation");
    backToAllCampuses.innerText = "back to campus listings"


    backToAllCampuses.addEventListener('click', () => {
        clearChildren(mainContent);
        fetch("http://localhost:8080/api/campuses")
            .then(response => response.json())
            .then(campuses => displayHomeView(campuses))
            .then(campusesElement => mainContent.appendChild(campusesElement))
            .catch(error => console.log(error));
    });

    campusLibraryElement.appendChild(backToAllCampuses);

}

const clearChildren = function (element) {
    while (element.firstChild) {
        element.removeChild(element.lastChild);
    }
}

export {
    displaySingleCampus,
    clearChildren
}