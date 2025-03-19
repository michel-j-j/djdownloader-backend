# DJ Downloader Backend

A robust backend service for DJ Downloader built with a **hexagonal architecture**. This service handles downloading songs from various streaming platforms, converting audio formats, managing metadata, and exposing RESTful APIs for integration with the frontend.

## 🎵 Features

- **Hexagonal Architecture**: Clean separation of concerns with domain-driven design.
- **Download Service**: Executes downloads using yt-dlp and handles format conversion.
- **Metadata Management**: Stores and retrieves song metadata and state.
- **RESTful API**: Provides endpoints to initiate downloads and query song data.
- **CORS Configuration**: Supports cross-origin requests for frontend integration.

## 🏗️ Architecture

The backend is structured according to the hexagonal (ports and adapters) architecture:

- **Domain Layer**  
  Contains core business logic:
  - **Models**: `Song`, `Metadata`, `State`
  - **Ports**: Outbound interfaces like `Downloader` and `SongRepository`
  - **Exceptions**: Domain exceptions such as `FormatNotFound` and `NotSongFound`

- **Use Case Layer**  
  Implements application-specific business logic (e.g., `DownloadService`) that orchestrates downloading and persistence.

- **Infrastructure Layer**  
  Adapters to interact with external systems:
  - REST controllers
  - Integration with yt-dlp (provided in the project root as `yt-dlp`)
  - CORS configuration (`CorsConfig`)
  - Application configuration via `application.properties`

## 📂 Project Structure

```
djdownloader-backend/
├── .idea/                      # IDE configuration files
├── .mvn/                       # Maven wrapper files
├── mvnw, mvnw.cmd              # Maven wrapper scripts
├── pom.xml                     # Maven project configuration
├── docker-compose.yml          # Docker Compose configuration
├── yt-dlp                      # yt-dlp executable for downloads
├── src/
│   ├── main/
│   │   ├── java/
│   │   │   └── com/djdownloader/
│   │   │       ├── DjdownloaderApplication.java  # Main application entry point
│   │   │       ├── CorsConfig.java               # CORS configuration
│   │   │       └── domain/
│   │   │           ├── model/
│   │   │           │   ├── Song.java             # Song domain model
│   │   │           │   ├── Metadata.java         # Metadata domain model
│   │   │           │   └── State.java              # State model
│   │   │           ├── port/
│   │   │           │   └── out/
│   │   │           │       ├── Downloader.java     # Downloader port interface
│   │   │           │       └── SongRepository.java   # Song repository port
│   │   │           ├── exception/
│   │   │           │   ├── FormatNotFound.java     # Exception for unsupported formats
│   │   │           │   └── NotSongFound.java       # Exception for missing songs
│   │   │           └── usecase/
│   │   │               └── DownloadService.java      # Download use case implementation
│   │   └── resources/
│   │       └── application.properties              # Spring Boot configuration
│   └── test/
│       └── java/
│           └── com/djdownloader/
│               └── DjdownloaderApplicationTests.java # Test cases
├── .gitignore
└── .gitattributes
```

## 🚀 Getting Started

### Prerequisites

- **Java 17** or higher
- **Maven 3.8** or higher
- **yt-dlp**: Ensure the executable is available (provided in the project root or install separately)
- **Docker** (optional, for containerized deployment)

### Installation

1. **Clone the repository:**
   ```bash
   git clone https://github.com/michel-j-j/djdownloader-backend.git
   ```
2. **Navigate to the project directory:**
   ```bash
   cd djdownloader-backend
   ```
3. **Build the project with Maven:**
   ```bash
   ./mvnw clean install
   ```

### Configuration

Update the application settings in `src/main/resources/application.properties`:
```properties
server.port=8080
yt-dlp.path=./yt-dlp
```
Adjust paths and other settings as needed for your environment.

### Running the Application

Start the application using Maven:
```bash
./mvnw spring-boot:run
```
The backend service will be accessible at [http://localhost:8080](http://localhost:8080).

### Docker

You can also run the application using Docker Compose:
```bash
docker-compose up --build
```

## 🔗 API Endpoints

The backend exposes the following RESTful endpoints:

- **POST** `/download`  
  Initiates a download process.  
  **Request Body Example:**
  ```json
  {
    "url": "https://music.youtube.com/watch?v=EXAMPLE",
    "format": "wav"
  }
  ```

## 🧪 Testing

Run unit and integration tests using Maven:
```bash
./mvnw test
```

## 📄 License

This project is licensed under the MIT License. See the [LICENSE](LICENSE) file for details.

## 🤝 Contributing

Contributions are welcome! Please follow these steps:
1. Fork the repository.
2. Create a new branch for your feature or bug fix.
3. Commit your changes with descriptive messages.
4. Push your branch and open a pull request.

---
