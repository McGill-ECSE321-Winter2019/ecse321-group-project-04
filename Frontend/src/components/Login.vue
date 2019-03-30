<!DOCTYPE html>
<template>
  <html lang="en">

  <head>
    <title>Bootstrap Example</title>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.0/css/bootstrap.min.css">
  </head>

  <body>

    <div class="container-fluid" id="top-container">
      <div class="container text-center" id="img-container">
        <img src="https://user-images.githubusercontent.com/35735496/54735369-2f1d7b80-4b7c-11e9-93a2-505866f8ec69.png" width="300" height="100">
      </div>
    </div>

    <div class="container-fluid text-center" id="background">
      <div class="col-sm-2 sidenav"></div>
      <div class="col-sm-8">
        <div class="row">
          <div class="col-sm-2"></div>
          <div class="col-sm-8">
            <br>
            <div class="container-fluid">
              <div class="container text-left" id="welcome">
                <h2>
                  <font>Welcome!</font>
                </h2>
              </div>
              <hr>
            </div>

            <div class="panel panel-default text-center">
              <div class="panel-body">
                <br><br><br><br>
                <div class="form-group">
                  <label for="usr">
                    <font size="4">Student Number:</font>
                  </label>
                  <input type="text" class="form-control form-control-lg" id="usr">
                  <p id="demo"></p>
                </div>
                <button @click="goToDashboard" type="button" class="btn btn-primary btn-block" id="login">
                  <font size="4"><b>Login</b></font>
                </button>
                <br>
                <a href="#" @click="showModalHelp=true"><i><b>Need help?</b></i></a>
              </div>
            </div>
          </div>
          <div class="col-sm-2"> </div>
        </div>
        <div id="footer">
          <div class="col-sm-2"> </div>
          <div class="col-sm-8">
            <hr>
            <br>
            <h3 id="message">{{getRandomMessage()}}</h3>
          </div>
          <div class="col-sm-2"> </div>
        </div>
      </div>
      <div class="col-sm-2 sidenav"></div>
    </div>

    <!-- URL Display Modal -->
    <transition name="modal">
      <div v-if="showModalHelp" key="helpModal">
        <div class="modal-mask">
          <div class="modal-wrapper" @click="showModalHelp=false">
            <div class="modal-container" @click.stop>
              <div class="modal-header">
                <slot name="header">
                  IT Help Information
                </slot>
              </div>
              <div class="modal-body">
                <h4 style="color:gray"><em>Email</em></h4>
                <h4><b>it.helpdesk@cooperator.ca</b></h4>
                <br>
                <h4 style="color:gray"><em>Phone</em></h4>
                <h4><b>438-382-2349</b></h4>
                <br>
              </div>
              <br>
              <div style="text-align:center">
                <slot>
                  <button class="btn btn-primary" style="min-width:120px" @click="showModalHelp=false">
                    <font size="3">Done</font>
                  </button>
                </slot>
              </div>
            </div>
          </div>
        </div>
      </div>
    </transition>

  </body>

  </html>
</template>

<style>
  #footer {
    position: fixed;
    left: 0;
    bottom: 50px;
    width: 100%;
    color: white;
    text-align: center;
  }

  .panel {
    min-height: 80%;
    max-width: 100%;
  }

  #top-container {
    margin-bottom: 0;
    margin-top: 0;
    background-color: #333335;
    color: #ffffff;
  }

  #welcome {
    max-width: 100%;
    margin-top: 0px;
  }

  #welcome h2 {
    text-align: center;
    margin-top: 15px;
    margin-bottom: 10px;
    font-size: 37px
  }

  #message {
    text-align: center;
    margin-top: 5px;
    margin-bottom: 0px;
    font-size: 25px;
    font-family: Lucida;
    font-style: oblique;
    color: #333335;
  }

  .modal-mask {
    position: fixed;
    z-index: 9998;
    top: 0;
    left: 0;
    width: 100%;
    height: 100%;
    background-color: rgba(0, 0, 0, .5);
    display: table;
    transition: opacity .3s ease;
  }

  .modal-wrapper {
    display: table-cell;
    vertical-align: middle;
  }

  .modal-container {
    width: 400px;
    margin: 0px auto;
    padding: 20px 30px;
    background-color: #fff;
    border-radius: 2px;
    box-shadow: 0 2px 8px rgba(0, 0, 0, .33);
    transition: all .3s ease;
    font-family: Helvetica, Arial, sans-serif;
  }

  .modal-header {
    margin-top: 0;
    font-size: 24px;
  }

  .modal-body {
    margin: 0;
  }

  .modal-default-button {
    float: right;
  }

  .modal-enter {
    opacity: 0;
  }

  .modal-leave-active {
    opacity: 0;
    transition: all .3s ease;
  }

  /*.modal-enter-active {
    opacity: 0;
    transition: all .3s ease;
  }*/

  .modal-enter .modal-container,
  .modal-leave-active .modal-container,
  .modal-leave-to .modal-container {
    /*-webkit-transform: scale(1.1);*/
    transform: translateY(100px);
  }
</style>

<script>
  import axios from 'axios'
  var config = require('../../config')

  /* AXIOS object setup */
  var frontendUrl = 'https://' + config.dev.host + ':' + config.dev.port
  var backendUrl = 'https://' + config.dev.backendHost //+ ':' + config.dev.backendPort

  var AXIOS = axios.create({
    baseURL: backendUrl
  })

  var checkInput = async (input) => {
    if (input.trim().search(/\d{9}/) == -1) {
      return 1; // Bad input
    } else {
      try {
        let response = await AXIOS.get(/students/ + input);
        if (response.data !== {}) {
          return 0; // Student records found
        } else {
          return 2; // No matching records
        }
      } catch (e) {
        return 3; // Error while making request
      }

    }
    return false;
  }

  var randomDisplay = 'puns'
  var quotes = [
    'Life is about making an impact, not making an income. -Kevin Kruse',
    'Whatever the mind of man can conceive and believe, it can achieve. –Napoleon Hill',
    'Strive not to be a success, but rather to be of value. –Albert Einstein',
    'Two roads diverged in a wood, and I—I took the one less traveled by, And that has made all the difference.  –Robert Frost',
    'I attribute my success to this: I never gave or took any excuse. –Florence Nightingale',
    'You miss 100% of the shots you don’t take. –Wayne Gretzky',
    'I\'ve missed more than 9000 shots in my career. I\'ve lost almost 300 games. 26 times I\'ve been trusted to take the game winning shot and missed. I\'ve failed over and over and over again in my life. And that is why I succeed. –Michael Jordan',
    'The most difficult thing is the decision to act, the rest is merely tenacity. –Amelia Earhart',
    'Every strike brings me closer to the next home run. –Babe Ruth',
    'Definiteness of purpose is the starting point of all achievement. –W. Clement Stone',
    'Life isn\'t about getting and having, it\'s about giving and being. –Kevin Kruse',
    'Life is what happens to you while you’re busy making other plans. –John Lennon',
    'We become what we think about. –Earl Nightingale',
    'Twenty years from now you will be more disappointed by the things that you didn’t do than by the ones you did do, so throw off the bowlines, sail away from safe harbor, catch the trade winds in your sails.  Explore, Dream, Discover. –Mark Twain',
    'Life is 10% what happens to me and 90% of how I react to it. –Charles Swindoll',
    'The most common way people give up their power is by thinking they don’t have any. –Alice Walker',
    'The mind is everything. What you think you become.  –Buddha',
    'The best time to plant a tree was 20 years ago. The second best time is now. –Chinese Proverb',
    'An unexamined life is not worth living. –Socrates',
    'Eighty percent of success is showing up. –Woody Allen',
    'Your time is limited, so don’t waste it living someone else’s life. –Steve Jobs',
    'Winning isn’t everything, but wanting to win is. –Vince Lombardi',
    'I am not a product of my circumstances. I am a product of my decisions. –Stephen Covey',
    'Every child is an artist.  The problem is how to remain an artist once he grows up. –Pablo Picasso',
    'You can never cross the ocean until you have the courage to lose sight of the shore. –Christopher Columbus',
    'I’ve learned that people will forget what you said, people will forget what you did, but people will never forget how you made them feel. –Maya Angelou',
    'Either you run the day, or the day runs you. –Jim Rohn',
    'Whether you think you can or you think you can’t, you’re right. –Henry Ford',
    'The two most important days in your life are the day you are born and the day you find out why. –Mark Twain',
    'Whatever you can do, or dream you can, begin it.  Boldness has genius, power and magic in it. –Johann Wolfgang von Goethe',
    'The best revenge is massive success. –Frank Sinatra',
    'People often say that motivation doesn’t last. Well, neither does bathing.  That’s why we recommend it daily. –Zig Ziglar',
    'Life shrinks or expands in proportion to one\'s courage. –Anais Nin',
    'If you hear a voice within you say “you cannot paint,” then by all means paint and that voice will be silenced. –Vincent Van Gogh',
    'There is only one way to avoid criticism: do nothing, say nothing, and be nothing. –Aristotle',
    'Ask and it will be given to you; search, and you will find; knock and the door will be opened for you. –Jesus',
    'The only person you are destined to become is the person you decide to be. –Ralph Waldo Emerson',
    'Go confidently in the direction of your dreams.  Live the life you have imagined. –Henry David Thoreau',
    'When I stand before God at the end of my life, I would hope that I would not have a single bit of talent left and could say, I used everything you gave me. –Erma Bombeck',
    'Few things can help an individual more than to place responsibility on him, and to let him know that you trust him.  –Booker T. Washington',
    'Certain things catch your eye, but pursue only those that capture the heart. – Ancient Indian Proverb',
    'Believe you can and you’re halfway there. –Theodore Roosevelt',
    'Everything you’ve ever wanted is on the other side of fear. –George Addair',
    'We can easily forgive a child who is afraid of the dark; the real tragedy of life is when men are afraid of the light. –Plato',
    'Teach thy tongue to say, "I do not know," and thous shalt progress. –Maimonides',
    'Start where you are. Use what you have.  Do what you can. –Arthur Ashe',
    'When I was 5 years old, my mother always told me that happiness was the key to life.  When I went to school, they asked me what I wanted to be when I grew up.  I wrote down ‘happy’.  They told me I didn’t understand the assignment, and I told them they didn’t understand life. –John Lennon',
    'Fall seven times and stand up eight. –Japanese Proverb',
    'When one door of happiness closes, another opens, but often we look so long at the closed door that we do not see the one that has been opened for us. –Helen Keller',
    'Everything has beauty, but not everyone can see. –Confucius',
    'How wonderful it is that nobody need wait a single moment before starting to improve the world. –Anne Frank',
    'When I let go of what I am, I become what I might be. –Lao Tzu',
    'Life is not measured by the number of breaths we take, but by the moments that take our breath away. –Maya Angelou',
    'Happiness is not something readymade.  It comes from your own actions. –Dalai Lama',
    'If you\'re offered a seat on a rocket ship, don\'t ask what seat! Just get on. –Sheryl Sandberg',
    'First, have a definite, clear practical ideal; a goal, an objective. Second, have the necessary means to achieve your ends; wisdom, money, materials, and methods. Third, adjust all your means to that end. –Aristotle',
    'If the wind will not serve, take to the oars. –Latin Proverb',
    'You can’t fall if you don’t climb.  But there’s no joy in living your whole life on the ground. –Unknown',
    'We must believe that we are gifted for something, and that this thing, at whatever cost, must be attained. –Marie Curie',
    'Too many of us are not living our dreams because we are living our fears. –Les Brown',
    'Challenges are what make life interesting and overcoming them is what makes life meaningful. –Joshua J. Marine',
    'If you want to lift yourself up, lift up someone else. –Booker T. Washington',
    'I have been impressed with the urgency of doing. Knowing is not enough; we must apply. Being willing is not enough; we must do. –Leonardo da Vinci',
    'Limitations live only in our minds.  But if we use our imaginations, our possibilities become limitless. –Jamie Paolinetti',
    'You take your life in your own hands, and what happens? A terrible thing, no one to blame. –Erica Jong',
    'What’s money? A man is a success if he gets up in the morning and goes to bed at night and in between does what he wants to do. –Bob Dylan',
    'I didn’t fail the test. I just found 100 ways to do it wrong. –Benjamin Franklin',
    'In order to succeed, your desire for success should be greater than your fear of failure. –Bill Cosby',
    'A person who never made a mistake never tried anything new. – Albert Einstein',
    'The person who says it cannot be done should not interrupt the person who is doing it. –Chinese Proverb',
    'There are no traffic jams along the extra mile. –Roger Staubach',
    'It is never too late to be what you might have been. –George Eliot',
    'You become what you believe. –Oprah Winfrey',
    'I would rather die of passion than of boredom. –Vincent van Gogh',
    'A truly rich man is one whose children run into his arms when his hands are empty. –Unknown',
    'It is not what you do for your children, but what you have taught them to do for themselves, that will make them successful human beings.  –Ann Landers',
    'If you want your children to turn out well, spend twice as much time with them, and half as much money. –Abigail Van Buren',
    'Build your own dreams, or someone else will hire you to build theirs. –Farrah Gray',
    'The battles that count aren\'t the ones for gold medals. The struggles within yourself--the invisible battles inside all of us--that\'s where it\'s at. –Jesse Owens',
    'Education costs money.  But then so does ignorance. –Sir Claus Moser',
    'I have learned over the years that when one\'s mind is made up, this diminishes fear. –Rosa Parks',
    'It does not matter how slowly you go as long as you do not stop. –Confucius',
    'If you look at what you have in life, you\'ll always have more. If you look at what you don\'t have in life, you\'ll never have enough. –Oprah Winfrey',
    'Remember that not getting what you want is sometimes a wonderful stroke of luck. –Dalai Lama',
    'You can’t use up creativity.  The more you use, the more you have. –Maya Angelou',
    'Dream big and dare to fail. –Norman Vaughan',
    'Our lives begin to end the day we become silent about things that matter. –Martin Luther King Jr.',
    'Do what you can, where you are, with what you have. –Teddy Roosevelt',
    'If you do what you’ve always done, you’ll get what you’ve always gotten. –Tony Robbins',
    'Dreaming, after all, is a form of planning. –Gloria Steinem',
    'It\'s your place in the world; it\'s your life. Go on and do all you can with it, and make it the life you want to live. –Mae Jemison',
    'You may be disappointed if you fail, but you are doomed if you don\'t try. –Beverly Sills',
    'Remember no one can make you feel inferior without your consent. –Eleanor Roosevelt',
    'Life is what we make it, always has been, always will be. –Grandma Moses',
    'The question isn’t who is going to let me; it’s who is going to stop me. –Ayn Rand',
    'When everything seems to be going against you, remember that the airplane takes off against the wind, not with it. –Henry Ford',
    'It’s not the years in your life that count. It’s the life in your years. –Abraham Lincoln',
    'Change your thoughts and you change your world. –Norman Vincent Peale',
    'Either write something worth reading or do something worth writing. –Benjamin Franklin',
    'Nothing is impossible, the word itself says, “I’m possible!” –Audrey Hepburn',
    'The only way to do great work is to love what you do. –Steve Jobs',
    'If you can dream it, you can achieve it. –Zig Ziglar'
  ]
  var puns = [
    'Dad, did you get a haircut? No I got them all cut.',
    'What do you call a Mexican who has lost his car? Carlos.',
    'Dad, can you put my shoes on? No, I don’t think they’ll fit me.',
    'Can I watch the TV? Dad: Yes, but don’t turn it on.',
    'I would avoid the sushi if I was you. It’s a little fishy.',
    'What do you call a fake noodle? An Impasta.',
    'You know, people say they pick their nose, but I feel like I was just born with mine.',
    '“Every time I hurt myself, even to this day, my dad says, ‘The good news is..it’ll feel better when it quits hurting.’”',
    'What’s brown and sticky? A stick.',
    'Want to hear a joke about paper? Nevermind it’s tearable.',
    'Did you hear about the restaurant on the moon? Great food, no atmosphere.',
    '“I’ll call you later!”- “Please don’t do that. I’ve always asked you to call me Dad!”',
    'Q: Why did the cookie cry? A: Because his father was a wafer so long!',
    'What did the mountain climber name his son? Cliff.',
    'This graveyard looks overcrowded. People must be dying to get in there.',
    '“My dad literally told me this one last week: ‘Did you hear about the guy who invented Lifesavers? They say he made a mint.’”',
    '“Whenever the cashier at the grocery store asks my dad if he would like the milk in a bag he replies, ‘No, just leave it in the carton!’”',
    'I got so angry the other day when I couldn’t find my stress ball.',
    'If I had a dime for every book I’ve ever read, I’d say: “Wow, that’s coincidental.”',
    'I’m not indecisive. Unless you want me to be.',
    'How many apples grow on a tree? All of them.',
    'How does a penguin build it’s house? Igloos it together.',
    '“Me: ‘Dad, make me a sandwich!’ Dad: ‘Poof, You’re a sandwich!’”',
    '“I heard there was a new store called Moderation. They have everything there',
    'A steak pun is a rare medium well done.',
    '“How can you tell if a ant is a boy or a girl? They’re all girls, otherwise they’d be uncles.”',
    'Milk is also the fastest liquid on earth – its pasteurized before you even see it',
    '“What’s Forrest Gump’s password? 1forrest1”',
    'The only thing worse than having diarrhea is having to spell it.',
    'I asked my friend to help me with a math problem. He said: “Don’t worry; this is a piece of cake.” I said: “No, it’s a math problem.”',
    'I keep trying to lose weight, but it keeps finding me.',
    'I don’t play soccer because I enjoy the sport. I’m just doing it for kicks.',
    'Did I tell you the time I fell in love during a backflip? I was heels over head.',
    'I used to work in a shoe recycling shop. It was sole destroying.',
    'Why do you never see elephants hiding in trees? Because they’re so good at it.',
    'Where did the one-legged waitress work? IHOP!',
    'What happened when the two antennas got married? Well, the ceremony was kinda boring, but the reception was great!',
    'What did one snowman say to the other one? “Do you smell carrots?”',
    'How do you make a tissue dance? You put a little boogie in it!',
    'Why did the blonde stare at the orange juice container? It said concentrate!',
    'If your nose runs and your feet smell, you are built upside down!',
    'I went to buy some camouflage trousers the other day, but I couldn’t find any.',
    'How do you organize an outer space party? A: You planet.',
    'What do you call a belt with a watch on it?A: A waist of time.',
    'What kind of shoes does a thief wear? Sneakers',
    'A jumper cable walks into a bar. The bartender says, “I’ll serve you, but don’t start anything.”'
  ]

  export default {
    name: 'login',
    data() {
      return {
        student: null,
        showModalHelp: false
      }
    },
    methods: {
      printOut: async function() {
        var result = await created();
        console.log(result);
      },
      getRandomMessage: function() {
        if (randomDisplay === 'puns') {
          return puns[Math.floor(Math.random() * puns.length)]
        } else if (randomDisplay === 'quotes') {
          return quotes[Math.floor(Math.random() * quotes.length)]
        }
      },
      goToDashboard: async function() {
        var input = document.getElementById("usr").value;
        var result = await checkInput(input);
        if (result == 0) {
          this.$router.push({
            name: 'Dashboard',
            params: {
              id: input
            }
          })
        } else {
          document.getElementById("usr").className = 'form-control form-control-lg is-invalid'
          document.getElementById("demo").style.color = 'red'
          if (result == 1) {
            document.getElementById("demo").innerHTML = "Please Enter A Valid Student ID";
          } else if (result == 2 || result == 3) {
            document.getElementById("demo").innerHTML = "There Are No Records For Student ID " + input;
          }
        };
        //var result = checkInput(input).then(ret);
        console.log("hey")

      },
    }
  }
</script>
