
# BlogSpace - Android Blog Application

## Description

BlogSpace is an Android application built with Kotlin that allows users to create, post, and read articles in a style similar to the Medium platform. This project demonstrates the use of modern Android development practices, including Firebase for user authentication and data storage, as well as Retrofit and Glide for network operations and image handling.

## Features

- **User Authentication:**
  - Sign up and log in using email and password.
  - Managed using Firebase Authentication.
- **Article Creation:**
  - Authenticated users can create new articles.
  - Each article includes a title and content.
  - Articles are associated with the author's user ID.
  - Timestamps for article creation.
- **Article Listing:**
  - The main screen displays a list of all articles in reverse chronological order (newest first).
  - Displays article title, content preview, and a placeholder image.
  - Utilizes `RecyclerView` for efficient list rendering.
- **Article Detail View:**
  - Displays the full content of an article when selected from the list.
  - Shows the article title, full content, and a placeholder image.
- **Data Storage:**
  - Firebase Firestore is used to store article data.
  - Articles are stored in a collection named "articles" with fields for ID, title, content, image URL (placeholder), author ID, and timestamp.

## Navigation

- **Login Screen** -> **Main Screen** (after successful login or signup)
- **Main Screen** -> **Login Screen** (if the user is not logged in and tries to add an article)
- **Main Screen** -> **Add Article Screen** (when the user taps the "Add Article" button)
- **Main Screen** -> **Article Detail Screen** (when the user taps on an article)
- **Add Article Screen** -> **Main Screen** (after successfully posting an article)
- **Sign-Up Screen** -> **Main Screen** (after successful signup)

## Technical Requirements

- **Language:** Kotlin
- **IDE:** Android Studio
- **Minimum SDK:** 26
- **Libraries:**
  - Firebase Authentication
  - Firebase Firestore
  - Firebase Realtime Database
  - RecyclerView
  - Glide
  - Material Design
  - Retrofit
  - Gson
  - OkHttp Logging Interceptor

## Setup

1.  **Firebase:**
    - Create a Firebase project in the Firebase console.
    - Add an Android app to your Firebase project.
    - Download the `google-services.json` file and place it in the `app` directory of your project.
    - Enable Email/Password authentication in the Firebase console.
    - Configure Firestore and Realtime Database rules as needed (ensure proper security rules for production).

2.  **Dependencies:**
    - Ensure all necessary dependencies are included in your `build.gradle.kts` (Module :app) file.

3.  **Permissions:**
    - Add necessary permissions (e.g., `INTERNET`) to your `AndroidManifest.xml`.

## Usage

1.  **Run the application** on an emulator or physical Android device.
2.  **Sign up** with a new account or **log in** with an existing one.
3.  **View articles** on the main screen.
4.  **Tap the "+" button** to navigate to the Add Article screen.
5.  **Create and post** a new article.
6.  **Tap on an article** in the main screen to view its full details.

## Further Improvements

- Implement image uploading functionality using an image hosting service (e.g., Imgur) or a custom backend.
- Add user profile management.
- Allow commenting on articles.
- Implement searching and filtering of articles.
- Add offline support for viewing articles.

## Contributing

Contributions to this project are welcome. Please fork the repository and submit a pull request with your proposed changes.

## License

This project is licensed under the [MIT License](LICENSE) - see the LICENSE file for details.

## Acknowledgements

- [Firebase Documentation](https://firebase.google.com/docs)
- [Android Developer Documentation](https://developer.android.com/docs)
- [Retrofit Documentation](https://square.github.io/retrofit/)
- [Glide Documentation](https://bumptech.github.io/glide/)
