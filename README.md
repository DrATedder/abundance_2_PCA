# abundance_2_PCA Java App

The "abundance_2_PCA" Java application is a graphical user interface (GUI) for running Principal Component Analysis (PCA) on species abundance data (typically generated from metagenomc analysis via centrifuge, but any appropriately formatted data will work). This app allows users to browse their computer to find the directory containing abundance files, select 2D or 3D PCA, choose to display variance, and generate PCA plots.

## Features

- Browse and select the input folder containing abundance files.
- Select between 2D and 3D PCA.
- Choose to display variance.
- Browse and select a metadata file (optional).
- Choose the output location for generated PCA plots.
- Generate PCA plots with a click of a button.
- Display the location of the generated PDF plot.
- Author information available.
- Option to produce another PCA run.

## Prerequisites

- Java Development Kit (JDK)
- Python (for the backend script)
- Python libraries: numpy, sklearn, pandas, matplotlib

## Usage

1. Clone the repository to your local machine:

   ```bash
   git clone https://github.com/yourusername/abundance_2_PCA.git
.
## Compile and run the java code
  javac abundance_2_PCA.java
  java abundance_2_PCA

## Author
Dr. Andrew Tedder
University of Bradford
