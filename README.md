# AskTBG - Meal Suggestion App

## Overview

AskTBG is an Android application built with Jetpack Compose that suggests meal ideas based on the time of day. Users can select a specific date and time, and the app will offer relevant meal suggestions in a carousel format. The app also allows users to share the meal suggestions with others.

## Features

* Time-Based Suggestions: Provides meal suggestions tailored to breakfast, lunch, and dinner times.
* Date and Time Selection: Allows users to pick a specific date and time for meal suggestions.
* Interactive Meal Carousel: Displays meal suggestions in a swipeable carousel with images and descriptions.
* Share Functionality: Enables users to share meal suggestions via other apps.
* Reset Option: Allows users to reset their date and time selection and clear suggestions.
* Live Clock: Displays the current time and date.
* Theming: Utilizes Material Design 3 for a modern and consistent user interface.

## Getting Started

1.  **Prerequisites:**
    * Android Studio installed on your system.
    * A physical Android device or an emulator.
    * Basic knowledge of Kotlin and Android development.

2.  **Installation:**
    * Clone this repository to your local machine.
    * Open the project in Android Studio.
    * Build and run the application on your device or emulator.

## Dependencies

The application uses the following main libraries and frameworks with their corresponding IEEE references:

* **Android SDK** ([27], [41], [42], [52], [68])
    * Provides the core functionalities for Android development.
* **AndroidX Annotation** ([43], [53], [71])
    * Used for providing support annotations.
* **Jetpack Compose**
    * **Foundation** ([28], [44], [54])
        * Provides fundamental building blocks for UI development.
    * **Foundation Layout** ([29], [45], [55])
        * Offers layout composables for arranging UI elements.
    * **Pager** ([30], [56])
        * Allows creating horizontally scrollable pages.
    * **Material Icons** ([31], [46])
        * Provides a set of pre-designed icons.
    * **Material 3** ([32], [47], [57])
        * Implements the Material Design 3 specification for theming and components.
    * **Runtime** ([33], [48], [58], [72])
        * Core runtime for Compose applications.
    * **UI** ([34], [49], [59])
        * Base UI components and functionalities.
    * **UI Graphics** ([35], [60])
        * APIs for drawing and graphics.
    * **UI Layout** ([36])
        * Specific layout functionalities.
    * **UI Tooling** ([73])
        * Tools for previewing and debugging Compose UI.
    * **UI Text** ([37])
        * APIs for handling text display and styling.
    * **UI Unit** ([38], [50], [62])
        * Provides unit types like `dp`.
* **Kotlin Coroutines** ([40], [66])
    * Used for managing asynchronous operations.
* **Java Time API** ([51], [67])
    * Provides classes for date and time manipulation.
* **Android Resources** ([63])
    * References to the project's resources (drawables, etc.).
* **Your Data Model** ([64])
    * Refers to the `Meal` data class.
* **Your Composable** ([65], [74])
    * Refers to the `MealAppScreenContent` and `MealAppScreenContainer` composables.
* **Your Theme** ([75])
    * Refers to the application's theme (`AskTBGTheme`).

* **`MainActivity.kt`**: The main activity that sets up the Compose UI.
* **`model/Meal.kt`**: Defines the data structure for a meal, including its name, description, and image resource ID.
* **`ui/components/`**: Contains reusable UI components like the logo, live clock, time selection, meal carousel, and action buttons.
* **`ui/mealscreen/MealAppScreenContainer.kt`**: A stateful composable that manages the overall state and logic of the meal suggestion screen.
* **`ui/theme/`**: Defines the visual theme of the application, including colors, typography, and shapes.
* **`R.kt`**: Auto-generated file containing references to the project's resources.

## Reference List (IEEE Style)

1.  Android Developers, “Build.VERSION_CODES,” developer.android.com, [Online]. Available: https://developer.android.com/reference/android/os/Build.VERSION_CODES. [Accessed: 04-Apr-2025].
2.  Android Developers, “Log,” developer.android.com, [Online]. Available: https://developer.android.com/reference/android/util/Log. [Accessed: 04-Apr-2025].
3.  Android Developers, “RequiresApi,” developer.android.com, [Online]. Available: https://developer.android.com/reference/androidx/annotation/RequiresApi. [Accessed: 04-Apr-2025].
4.  Android Developers, “ExperimentalFoundationApi,” developer.android.com/reference/androidx/compose/foundation/ExperimentalFoundationApi. [Accessed: 04-Apr-2025].
5.  Android Developers, “BorderStroke,” developer.android.com/reference/androidx/compose/foundation/BorderStroke. [Accessed: 04-Apr-2025].
6.  Android Developers, “Image,” developer.android.com/reference/androidx/compose/foundation/Image. [Accessed: 04-Apr-2025].
7.  Android Developers, “background,” developer.android.com/reference/androidx/compose/foundation/BackgroundKt. [Accessed: 04-Apr-2025].
8.  Android Developers, “border,” developer.android.com/reference/androidx/compose/foundation/BorderKt. [Accessed: 04-Apr-2025].
9.  Android Developers, “Arrangement,” developer.android.com/reference/androidx/compose/foundation/layout/Arrangement. [Accessed: 04-Apr-2025].
10. Android Developers, “Box,” developer.android.com/reference/androidx/compose/foundation/layout/Box. [Accessed: 04-Apr-2025].
11. Android Developers, “Column,” developer.android.com/reference/androidx/compose/foundation/layout/Column. [Accessed: 04-Apr-2025].
12. Android Developers, “Row,” developer.android.com/reference/androidx/compose/foundation/layout/Row. [Accessed: 04-Apr-2025].
13. Android Developers, “Spacer,” developer.android.com/reference/androidx/compose/foundation/layout/Spacer. [Accessed: 04-Apr-2025].
14. Android Developers, “aspectRatio,” developer.android.com/reference/androidx/compose/foundation/layout/SizeKt. [Accessed: 04-Apr-2025].
15. Android Developers, “fillMaxHeight,” developer.android.com/reference/androidx/compose/foundation/layout/SizeKt. [Accessed: 04-Apr-2025].
16. Android Developers, “fillMaxSize,” developer.android.com/reference/androidx/compose/foundation/layout/SizeKt. [Accessed: 04-Apr-2025].
17. Android Developers, “fillMaxWidth,” developer.android.com/reference/androidx/compose/foundation/layout/SizeKt. [Accessed: 04-Apr-2025].
18. Android Developers, “height,” developer.android.com/reference/androidx/compose/foundation/layout/SizeKt. [Accessed: 04-Apr-2025].
19. Android Developers, “padding,” developer.android.com/reference/androidx/compose/foundation/layout/PaddingKt. [Accessed: 04-Apr-2025].
20. Android Developers, “size,” developer.android.com/reference/androidx/compose/foundation/layout/SizeKt. [Accessed: 04-Apr-2025].
21. Android Developers, “HorizontalPager,” developer.android.com/reference/androidx/compose/foundation/pager/HorizontalPager. [Accessed: 04-Apr-2025].
22. Android Developers, “PagerState,” developer.android.com/reference/androidx/compose/foundation/pager/PagerState. [Accessed: 04-Apr-2025].
23. Android Developers, “CircleShape,” developer.android.com/reference/androidx/compose/foundation/shape/CircleShape. [Accessed: 04-Apr-2025].
24. Android Developers, “RoundedCornerShape,” developer.android.com/reference/androidx/compose/foundation/shape/RoundedCornerShape. [Accessed: 04-Apr-2025].
25. Android Developers, “Icons,” developer.android.com/reference/androidx/compose/material/icons/Icons. [Accessed: 04-Apr-2025].
26. Android Developers, “Icon,” developer.android.com/reference/androidx/compose/material3/Icon. [Accessed: 04-Apr-2025].
27. Android Developers, “IconButton,” developer.android.com/reference/androidx/compose/material3/IconButton. [Accessed: 04-Apr-2025].
28. Android Developers, “MaterialTheme,” developer.android.com/reference/androidx/compose/material3/MaterialTheme. [Accessed: 04-Apr-2025].
29. Android Developers, “Text,” developer.android.com/reference/androidx/compose/material3/Text. [Accessed: 04-Apr-2025].
30. Android Developers, “Button,” developer.android.com/reference/androidx/compose/material3/Button. [Accessed: 04-Apr-2025].
31. Android Developers, “ButtonDefaults,” developer.android.com/reference/androidx/compose/material3/ButtonDefaults. [Accessed: 04-Apr-2025].
32. Android Developers, “OutlinedTextField,” developer.android.com/reference/androidx/compose/material3/OutlinedTextField. [Accessed: 04-Apr-2025].
33. Android Developers, “OutlinedTextFieldDefaults,” developer.android.com/reference/androidx/compose/material3/OutlinedTextFieldDefaults. [Accessed: 04-Apr-2025].
34. Android Developers, “DatePicker,” developer.android.com/reference/androidx/compose/material3/DatePicker. [Accessed: 04-Apr-2025].
35. Android Developers, “DatePickerDialog,” developer.android.com/reference/androidx/compose/material3/DatePickerDialog. [Accessed: 04-Apr-2025].
36. Android Developers, “rememberDatePickerState,” developer.android.com/reference/androidx/compose/material3/rememberDatePickerState. [Accessed: 04-Apr-2025].
37. Android Developers, “Composable,” developer.android.com/reference/androidx/compose/runtime/Composable. [Accessed: 04-Apr-2025].
38. Android Developers, “LaunchedEffect,” developer.android.com/reference/androidx/compose/runtime/LaunchedEffect. [Accessed: 04-Apr-2025].
39. Android Developers, “derivedStateOf,” developer.android.com/reference/androidx/compose/runtime/derivedStateOf. [Accessed: 04-Apr-2025].
40. Android Developers, “mutableStateOf,” developer.android.com/reference/androidx/compose/runtime/mutableStateOf. [Accessed: 04-Apr-2025].
41. Android Developers, “remember,” developer.android.com/reference/androidx/compose/runtime/remember. [Accessed: 04-Apr-2025].
42. Android Developers, “rememberCoroutineScope,” developer.android.com/reference/androidx/compose/runtime/rememberCoroutineScope. [Accessed: 04-Apr-2025].
43. Android Developers, “Alignment,” developer.android.com/reference/androidx/compose/ui/Alignment. [Accessed: 04-Apr-2025].
44. Android Developers, “Modifier,” developer.android.com/reference/androidx/compose/ui/Modifier. [Accessed: 04-Apr-2025].
45. Android Developers, “clip,” developer.android.com/reference/androidx/compose/ui/draw/ClipKt. [Accessed: 04-Apr-2025].
46. Android Developers, “Color,” developer.android.com/reference/androidx/compose/ui/graphics/Color. [Accessed: 04-Apr-2025].
47. Android Developers, “ContentScale,” developer.android.com/reference/androidx/compose/ui/layout/ContentScale. [Accessed: 04-Apr-2025].
48. Android Developers, “painterResource,” developer.android.com/reference/androidx/compose/ui/res/PainterResourcesKt. [Accessed: 04-Apr-2025].
49. Android Developers, “FontWeight,” developer.android.com/reference/androidx/compose/ui/text/font/FontWeight. [Accessed: 04-Apr-2025].
50. Android Developers, “TextAlign,” developer.android.com/reference/androidx/compose/ui/text/style/TextAlign. [Accessed: 04-Apr-2025].
51. Android Developers, “dp,” developer.android.com/reference/androidx/compose/ui/unit/Dp. [Accessed: 04-Apr-2025].
52. Com.example.asktbg, “Meal Model,” github.com/asktbg/meal-model, [Online]. Available: https://github.com/asktbg/meal-model. [Accessed: 04-Apr-2025].
53. Kotlin Coroutines, “CoroutineScope,” kotlinlang.org, [Online]. Available: https://kotlinlang.org/docs/coroutines-core.html. [Accessed: 04-Apr-2025].
54. Java SE, “LocalDate,” docs.oracle.com/javase/8/docs/api/java/time/LocalDate.html, [Online]. Available: https://docs.oracle.com/javase/8/docs/api/java/time/LocalDate.html. [Accessed: 04-Apr-2025].
55. Java SE, “LocalTime,” docs.oracle.com/javase/8/docs/api/java/time/LocalTime.html, [Online]. Available: https://docs.oracle.com/javase/8/docs/api/java/time/LocalTime.html. [Accessed: 04-Apr-2025].
56. Java SE, “Instant,” docs.oracle.com/javase/8/docs/api/java/time/Instant.html, [Online]. Available: https://docs.oracle.com/javase/8/docs/api/java/time/Instant.html. [Accessed: 04-Apr-2025].
57. Java SE, “ZoneId,” docs.oracle.com/javase/8/docs/api/java/time/ZoneId.html, [Online]. Available: https://docs.oracle.com/javase/8/docs/api/java/time/ZoneId.html. [Accessed: 04-Apr-2025].
58. Java SE, “DateTimeFormatter,” docs.oracle.com/javase/8/docs/api/java/time/format/DateTimeFormatter.html, [Online]. Available: https://docs.oracle.com/javase/8/docs/api/java/time/format/DateTimeFormatter.html. [Accessed: 04-Apr-2025].
59. Java SE, “FormatStyle,” docs.oracle.com/javase/8/docs/api/java/time/format/FormatStyle.html, [Online]. Available: https://docs.oracle.com/javase/8/docs/api/java/time/format/FormatStyle.html. [Accessed: 04-Apr-2025].
60. Android Developers, “ComponentActivity,” developer.android.com/reference/androidx/activity/ComponentActivity. [Accessed: 04-Apr-2025].
61. Android Developers, “setContent,” developer.android.com/reference/androidx/activity/compose/ComponentActivityKt. [Accessed: 04-Apr-2025].
62. Android Developers, “Brush,” developer.android.com/reference/androidx/compose/ui/graphics/Brush. [Accessed: 04-Apr-2025].
63. Android Developers, “LocalContext,” developer.android.com/reference/androidx/compose/ui/platform/LocalContext. [Accessed: 04-Apr-2025].
64. Android Developers, “rememberPagerState,” developer.android.com/reference/androidx/compose/foundation/pager/rememberPagerState. [Accessed: 04-Apr-2025].
65. Android Developers, “AskTBGTheme,” [Online]. (Note: Replace with the actual link to your theme documentation or source if available). [Accessed: 04-Apr-2025].



https://youtu.be/X8VK8G1u1w0
https://github.com/TebogoMsimanga/Ask-TBG

