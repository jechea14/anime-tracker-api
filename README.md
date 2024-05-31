An anime tracker API where users track their anime in their anime watch list. Users register to create an account then log in to add, edit, and delete their anime watching progress in their anime watch list. Users automatically obtain the User Role after registering. Admin roles are given to users manually.

API Endpoints:
- User Anime List (Logged in users can access their own lists)
  - GET `/api/v1/animelist` : Get all anime for the logged in user
  - GET `/api/v1/animelist/{animeId}` : Get details of a specific anime watch detail from the logged in user's list
  - POST `/api/v1/animelist/{animeId}` : Add a new anime entry and watch detail for the logged in user
  - PUT `/api/v1/animelist/{animeId}` : Update an anime entry and watch detail for the logged in user
  - DELETE '`/api/v1/animelist/{animeId}` : Delete an anime entry and watch detail for the logged in user
    
- Authentication (Anyone can access these endpoints)
  - POST `/auth/register` : Register user with username and password. Returns JWT
  - POST `/auth/login` : Login user with username, password, and JWT
 
- Anime (Anyone can access the GET endpoints. Admins have authorization to the rest of the endpoints)
  - GET `/api/v1/anime` : Get all anime
  - GET `/api/v1/anime/{id}` : Get a certain anime based on its id
  - POST `/api/v1/anime` : Create anime
  - PUT `/api/v1/anime/{id}` : Update anime based on its id
  - DELETE `/api/v1/anime/{id}` : Delete anime based on its id
    
- Genres (Anyone can access the GET endpoints. Admins have authorization to the rest of the endpoints)
  - GET `/api/v1/genres` : Get all genres
  - GET `/api/v1/genres/{id}` : Get a certain genre based on its id
  - POST `/api/v1/genres` : Create genre
  - PUT `/api/v1/genres/{id}` : Update genre based on its id
  - DELETE `/api/v1/genres/{id}` : Delete genre based on its id
    
- Users (Only admins have authorization to this endpoint)
  - GET `/api/v1/users` : Get all users
  - GET `/api/v1/users/{id}` : Get a certain user based on its id
  - PUT `/api/v1/users/{id}` : Update user based on its id
  - DELETE `/api/v1/users/{id}` : Delete user based on its id
