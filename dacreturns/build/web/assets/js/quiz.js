(function() {
  const myQuestions = [
    {
      img: "assets/images/exercicios/1.png",
      question: "Qual a saída na impressão?",
      answers: {
        a: "A resposta e: 12 ",
        b: "A resposta e: 6 ",
        c: "A resposta e: 4 "
      },
      correctAnswer: "b"
    },
    {
      img: "assets/images/exercicios/2.png",
      question: "Qual valor será mostrado?",
      answers: {
        a: "47",
        b: "32",
        c: "Nenhum"
      },
      correctAnswer: "a"
    },
    {
      img: "assets/images/exercicios/3.png",
      question: "Qual o valor da variável &quotinc&quot?",
      answers: {
        a: "14",
        b: "19",
        c: "27"
      },
      correctAnswer: "c"
    },
    {
      img: "assets/images/exercicios/4.png",
      question: "Este laço é infinito, qual o motivo?",
      answers: {
        a: "A comparação não é válida então nunca sai do laço",
        b: "A condição de parada nunca é alcançada",
        c: "Todo laço desse tipo é infinito"
      },
      correctAnswer: "b"
    },
    {
      img: "assets/images/exercicios/5.png",
      question: "Sobre vetores, é coreto afirmar que:",
      answers: {
        a: "Apenas a alternativa III está correta",
        b: "Apenas a alternativa I está correta",
        c: "Apenas as alternativas II e III estão corretas"
      },
      correctAnswer: "a"
    },
    {
      img: "assets/images/exercicios/6.png",
      question: "Qual valor será impresso?",
      answers: {
        a: "23",
        b: "6",
        c: "67"
      },
      correctAnswer: "b"
    },
    {
      img: "assets/images/exercicios/7.png",
      question: "o que o comando - if - representa?",
      answers: {
        a: "Loop",
        b: "Condicional",
        c: "Declaração de variáveis"
      },
      correctAnswer: "b"
    },
    {
      img: "assets/images/exercicios/8.png",
      question: "O CodeBLocks é um(a):",
      answers: {
        a: "Interface para desenvolvimento de programas",
        b: "É um compilador para códigos C",
        c: "É um programa que serve para execução de códigos em C"
      },
      correctAnswer: "a"
    },
    {
      img: "assets/images/exercicios/9.png",
      question: "Qual resultado será impresso?",
      answers: {
        a: "23 67 6 99 11 44 3",
        b: "6 44",
        c: "23 67 99 11 3"
      },
      correctAnswer: "b"
    },
    {
      img: "assets/images/exercicios/10.png",
      question: "Qual será o resultado final?",
      answers: {
        a: "Soma: 11",
        b: "Soma: 13",
        c: "Soma: 15"
      },
      correctAnswer: "c"
    }

  ];

  function buildQuiz() {
    // we'll need a place to store the HTML output
    const output = [];

    // for each question...
    myQuestions.forEach((currentQuestion, questionNumber) => {
      // we'll want to store the list of answer choices
      const answers = [];

      answers.push(
        `<img style="border: black solid 4px" = '' src="${currentQuestion.img}"/><br><br>`
      );
      // and for each available answer...
      for (letter in currentQuestion.answers) {
        // ...add an HTML radio button
        answers.push(
          `<label>
             <input type="radio" name="question${questionNumber}" value="${letter}">
              ${letter} :
              ${currentQuestion.answers[letter]}
           </label>`
        );
      }

      // add this question and its answers to the output
      output.push(
        `<div class="slide">
           <div class="question"> ${currentQuestion.question} </div>
           <div class="answers"> ${answers.join("")} </div>
         </div>`
      );
    });

    // finally combine our output list into one string of HTML and put it on the page
    quizContainer.innerHTML = output.join("");
  }

  function showResults() {
    // gather answer containers from our quiz
    const answerContainers = quizContainer.querySelectorAll(".answers");

    // keep track of user's answers
    let numCorrect = 0;

    // for each question...
    myQuestions.forEach((currentQuestion, questionNumber) => {
      // find selected answer
      const answerContainer = answerContainers[questionNumber];
      const selector = `input[name=question${questionNumber}]:checked`;
      const userAnswer = (answerContainer.querySelector(selector) || {}).value;

      // if answer is correct
      if (userAnswer === currentQuestion.correctAnswer) {
        // add to the number of correct answers
        numCorrect++;

        // color the answers green
        answerContainers[questionNumber].style.color = "lightgreen";
      } else {
        // if answer is wrong or blank
        // color the answers red
        answerContainers[questionNumber].style.color = "red";
      }
      console.log(numCorrect);
      document.getElementById("resp").setAttribute('value', numCorrect);
    });

    // show number of correct answers out of total
    resultsContainer.innerHTML = `<br><h3>Acertou ${numCorrect} de ${myQuestions.length}</h3>`;
  }

  function showSlide(n) {
    slides[currentSlide].classList.remove("active-slide");
    slides[n].classList.add("active-slide");
    currentSlide = n;
    
    if (currentSlide === 0) {
      previousButton.style.display = "none";
    } else {
      previousButton.style.display = "inline-block";
    }
    
    if (currentSlide === slides.length - 1) {
      nextButton.style.display = "none";
      submitButton.style.display = "inline-block";
    } else {
      nextButton.style.display = "inline-block";
      submitButton.style.display = "none";
    }
  }

  function showNextSlide() {
    showSlide(currentSlide + 1);
  }

  function showPreviousSlide() {
    showSlide(currentSlide - 1);
  }

  const quizContainer = document.getElementById("quiz");
  const resultsContainer = document.getElementById("results");
  const submitButton = document.getElementById("submit");

  // display quiz right away
  buildQuiz();

  const previousButton = document.getElementById("previous");
  const nextButton = document.getElementById("next");
  const slides = document.querySelectorAll(".slide");
  let currentSlide = 0;

  showSlide(0);

  // on submit, show results
  submitButton.addEventListener("click", showResults);
  previousButton.addEventListener("click", showPreviousSlide);
  nextButton.addEventListener("click", showNextSlide);


})();

