# SharingApp

SharingApp is a Java-based Android application designed to help users manage their personal belongings and track items lent to contacts. It provides a simple and intuitive interface for item and contact management.

## Features

- **Item Management**: 
  - Add new items with titles, makers, descriptions, and dimensions.
  - Attach images to items.
  - Edit or delete existing items.
- **Contact Management**: 
  - Maintain a list of contacts with usernames and emails.
  - Edit or delete contact information.
- **Lending System**: 
  - Track whether an item is "Available" or "Borrowed".
  - Assign a specific contact as a borrower for an item.
- **Organized Views**: 
  - Tabbed interface to quickly switch between **All Items**, **Available Items**, and **Borrowed Items**.
- **Local Persistence**: 
  - All data is saved locally on the device using GSON for JSON serialization, ensuring your data is available offline.

## Architecture

The project follows a modular structure and incorporates common design patterns:

- **Command Pattern**: Used for data operations (Add, Edit, Delete) to decouple the UI from the underlying data management logic.
- **MVC-like Structure**:
  - **Models**: `Item`, `Contact`, `Dimensions`.
  - **Controllers/Lists**: `ItemList` and `ContactList` handle the business logic and data persistence.
  - **Views**: Android Activities and Fragments.
- **Persistence Layer**: Data is serialized to `.sav` files using the GSON library.

## Getting Started

### Prerequisites

- Android Studio 4.0 or higher.
- Android SDK version 29.
- Minimum Android version: API 19 (KitKat).

### Installation

1. Clone the repository to your local machine.
2. Open the project in Android Studio.
3. Sync the project with Gradle files.
4. Run the application on an emulator or a physical Android device.

## Project Structure

- `app/src/main/java/com/example/sharingapp/`:
    - **Activities**: UI entry points for different features (Main, Add/Edit Item, Contacts).
    - **Fragments**: Specialized views for item categories.
    - **Commands**: Implementation of the Command pattern for item actions.
    - **Models**: Core data classes.
- `app/src/main/res/`: UI resources including layouts, menus, and values.

## Dependencies

- `androidx.appcompat:appcompat:1.2.0`
- `com.google.android.material:material:1.2.1`
- `com.google.code.gson:gson` (via local jar)
- `androidx.legacy:legacy-support-v4:1.0.0`

---
*Developed as a practical project for learning Android development patterns.*
