# Todo App

This Todo app is designed to help you organize your tasks efficiently. It provides a user-friendly interface for managing and keeping track of your daily tasks.

## Features

- Add, edit, and delete tasks
- Mark tasks as completed
- View a list of pending and completed tasks
- Store tasks locally for offline access
- User-friendly and intuitive interface

## Libraries Used

- [Room](https://developer.android.com/training/data-storage/room) - Local database for storing tasks
- [ViewModel](https://developer.android.com/topic/libraries/architecture/viewmodel) - Architecture component for managing UI-related data
- [Navigation Component](https://developer.android.com/guide/navigation) - For handling in-app navigation
- [Dagger Hilt](https://dagger.dev/hilt/) - For dependency injection
- [Coroutines](https://developer.android.com/kotlin/coroutines) - For managing asynchronous tasks
- [Flows](https://developer.android.com/kotlin/flow) - Data holder class for receiving live updates from a database


## Architecture
The app follows the MVVM (Model-View-ViewModel) architecture, which separates the business logic from the UI. The components are structured as follows:

- Model: Manages the data and business logic, including Room entities and repositories.
- View: Implemented using Jetpack Compose, responsible for displaying the UI components.
- ViewModel: Manages UI-related data and interactions, communicates with the repository and provides data to the UI.

## Requirements

- Android SDK version 21 or above
- Android Studio version 4.0 or above
- Kotlin version 1.4.0 or above

## Getting Started

To run the app, follow these steps:

1. Clone the repository:

   ```bash
   git clone https://github.com/your-username/TodoApp.git
   ```

2. Open the project in Android Studio.

3. Build and run the project on an emulator or physical device.

## Screenshots
(![WhatsApp Image 2023-10-16 at 11.27.55.jpeg](videos%20and%20pics%2FWhatsApp%20Image%202023-10-16%20at%2011.27.55.jpeg))
(![WhatsApp Image 2023-10-16 at 11.30.36.jpeg](videos%20and%20pics%2FWhatsApp%20Image%202023-10-16%20at%2011.30.36.jpeg))
(![WhatsApp Image 2023-10-16 at 11.30.37.jpeg](videos%20and%20pics%2FWhatsApp%20Image%202023-10-16%20at%2011.30.37.jpeg))
(![WhatsApp Image 2023-10-16 at 11.30.37 (1).jpeg](videos%20and%20pics%2FWhatsApp%20Image%202023-10-16%20at%2011.30.37%20%281%29.jpeg))




## Contributing

Contributions are always welcome! If you have any ideas or suggestions to improve the app, please feel free to open an issue or submit a pull request with a detailed description of what you've done or added.



## Acknowledgements

- This app was created with guidance from various Android development tutorials and documentation provided by Google.
- Special thanks to the open-source community for their valuable contributions and support.

## Contact

If you have any questions or suggestions, please feel free to reach out to the project maintainers:

Your Name - [Esther Cynthia](mailto:essyc14@gmail.com)

Project Link: [https://github.com/essy16/Todo](https://github.com/essy16/Todo)
