db = db.getSiblingDB('cmdb');
db.createUser({
    user: 'cmuser',
    pwd: 'cmpass',
    roles: [{
        role: 'readWrite',
        db: 'cmdb',
    }]
});

db.createCollection('shows');

// Insert data into the shows collection
db.shows.insertMany([
    {
        showId: 1,
        abstractDesc: "Ryan Wilder, armed with a passion for justice and a flair for speaking her mind, soars onto the streets of Gotham as Batwoman, a highly trained street fighter primed to snuff out the failing city's criminal resurgence. But don't call her a hero yet. In a city desperate for a savior, Ryan must overcome her own demons before embracing the call to be Gotham's symbol of hope.",
        ageRating: 12,
        availabilityFromUtcIso: ISODate("2019-12-21T22:00:00Z"),
        backgroundUrl: "https://m.media-amazon.com/images/M/MV5BNWE4MGVhZjUtMjEwNy00NzJlLWE3ZmEtNWMyYmFlZmUzZTA0XkEyXkFqcGdeQXVyMTkxNjUyNQ@@._V1_SX300.jpg",
        cast: "Ruby Rose, Rachel Skarsten, Meagan Tandy, Camrus Johnson, Nicole Kang",
        category: "SERIES",
        director: "Marcos Siega",
        editedAbstract: "Ryan Wilder, armed with a passion for justice and a flair for speaking her mind, soars onto the streets of Gotham as Batwoman, a highly trained street fighter primed to snuff out the failing city's criminal resurgence. But don't call her a hero yet. In a city desperate for a savior, Ryan must overcome her own demons before embracing the call to be Gotham's symbol of hope.",
        genre: "action",
        imdbId: "tt8712204",
        name: "Batwoman",
        productionYear: 2019,
        seasons: 3
    },
    {
        showId: 2,
        abstractDesc: "Inspired by the true life story of NFL Superbowl Champion, Spencer Paysinger, All American is an inspiring, ensemble family drama about a young, high school football phenom, Spencer James and the two families whose homes he shares after transferring from Crenshaw to Beverly High - his mother and brother in South Central LA and the Bakers of Beverly Hills.",
        ageRating: 12,
        availabilityFromUtcIso: ISODate("2018-10-11T05:00:00Z"),
        backgroundUrl: "https://m.media-amazon.com/images/M/MV5BMzBiMTE0NmQtZTM5Yi00ZDZhLWE2MDQtMzU2ZmIxYmQ4ZmFjXkEyXkFqcGdeQXVyMTkxNjUyNQ@@._V1_SX300.jpg",
        cast: "Daniel Ezra, Greta Onieogou, Samantha Logan, Michael Evans Behling, Taye Diggs",
        category: "SERIES",
        director: "Rob Hardy",
        editedAbstract: "Inspired by the true life story of NFL Superbowl Champion, Spencer Paysinger...",
        genre: "drama",
        imdbId: "tt7414406",
        name: "Spencer",
        productionYear: 2018,
        seasons: 4
    },
    {
        showId: 3,
        abstractDesc: "Based in 1970s New York, this show gives a raw, gritty portrayal of the prostitution business that was so publicly executed at that time.",
        ageRating: 18,
        availabilityFromUtcIso: ISODate("2017-09-23T20:00:00Z"),
        backgroundUrl: "https://m.media-amazon.com/images/M/MV5BOWEyM2U1MjctZTUzYy00MTMyLTkyNTgtNjQxNTc2ODk2OTJkXkEyXkFqcGdeQXVyMzQ2MDI5NjU@._V1_SX300.jpg",
        cast: "James Franco, Maggie Gyllenhaal, Gary Carr, Margarita Levieva, Lawrence Gilliard Jr.",
        category: "SERIES",
        director: "Michelle Maclaren",
        editedAbstract: "Based in 1970s New York...",
        genre: "drama",
        imdbId: "tt4998350",
        name: "The Deuce",
        productionYear: 2017,
        seasons: 3
    },
    {
        showId: 4,
        abstractDesc: "Continuing the tradition of The Vampire Diaries and The Originals, the story of the next generation of supernatural beings at The Salvatore School for the Young and Gifted.",
        ageRating: 15,
        availabilityFromUtcIso: ISODate("2018-10-26T05:00:00Z"),
        backgroundUrl: "https://m.media-amazon.com/images/M/MV5BZmMwNDczMDUtNDU0Mi00MjIyLWI1NTktNzM4Yzc1MTNmZDIxXkEyXkFqcGdeQXVyOTQ0NTEzMzk@._V1_SX300.jpg",
        cast: "Danielle Rose Russell, Matthew Davis, Jenny Boyd, Kaylee Bryant, Quincy Fouse",
        category: "SERIES",
        director: "Chris Grismer",
        editedAbstract: "Continuing the tradition of The Vampire Diaries...",
        genre: "drama",
        imdbId: "tt8103070",
        name: "Legacies",
        productionYear: 2018,
        seasons: 4
    },
    {
        showId: 5,
        abstractDesc: "Eszter has a crush on her English teacher, but he announces that he will leave the county for a foreign job. So she starts a sexting relationship with him.",
        ageRating: 12,
        availabilityFromUtcIso: ISODate("2019-06-05T20:00:00Z"),
        backgroundUrl: "https://m.media-amazon.com/images/M/MV5BYTY1NWQ0YTItYWZmOC00N2Q1LWE0YjItYzNiZDFiZmJjYjYwXkEyXkFqcGdeQXVyMzI2OTY0NjA@._V1_SX300.jpg",
        cast: "Szilvia Herr, Kristóf Vajda, Csaba Polgár, Judit Rezes, Dávid Rácz",
        category: "MOVIE",
        director: "Mihály Schwechtje",
        editedAbstract: "Eszter has a crush on her English teacher...",
        genre: "drama",
        imdbId: "tt8529512",
        name: "Remélem legközelebb sikerül meghalnod :)",
        productionYear: 2018
    }
]);