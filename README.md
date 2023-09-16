# abundance_2_PCA Java App

The "abundance_2_PCA" Java application is a graphical user interface (GUI) for running Principal Component Analysis (PCA) on species abundance data (typically generated from metagenomc analysis via centrifuge, but any appropriately formatted data will work). This app allows users to browse their computer to find the directory containing abundance files, select 2D or 3D PCA, choose to display variance, and generate PCA plots.

## Appearance

![Screenshot](https://github.com/DrATedder/abundance_2_PCA/blob/c1853738776046ae1140c9bd293b1b6a32273ef0/abundance_2_PCA.png "Screenshot of abundance_2_PCA App")

## Features

- Browse and select the input folder containing abundance files.
- Select between 2D and 3D PCA.
- Choose to display variance.
- Browse and select a metadata file.
- Choose the output location for generated PCA plots.
- Generate PCA plots with a click of a button.
- Display the location of the generated PDF plot.
- Option to produce another PCA run.

## Prerequisites

- Java Development Kit (JDK)
- Python (for the backend script)
- Python libraries: numpy, sklearn, pandas, matplotlib

## Usage

Clone the repository to your local machine:

   ```bash
   git clone https://github.com/yourusername/abundance_2_PCA.git
   ```
   
## Compile and run the java code
   
   ```bash
   javac abundance_2_PCA.java
   java abundance_2_PCA
   ```

## Example input files
### Abundance files
Input files (minimum 2) containing abundance data should be in three column CSV file format (example format shown below) with a 'txt' extension. The columns represent 'species', 'read count' and 'abundance'. No column headers are permitted.
An example input file (ERR9638312_fastp_trimmed_decon_centrifugeReport_abundance.txt) can be found [here](../blob/8a56fd9c63c5e9359ce89a43b8392921ac85cd3c/ERR9638312_fastp_trimmed_decon_centrifugeReport_abundance.txt "Example Input Data").

All files should be named in the following way: 
> shortname_anything_abundance.txt

1. **shortname**: used to label samples in the PCA plot; should also be used in metadata file
2. **anything**: not used, but can be anything
3. **abundance.txt**: used by the programme to identify the correct files within the given directory
4. **underscores** ('_') must be used between file name elements as these are used for splitting file names


|     |     |     |
| --- | --- | --- |
|Azorhizobium caulinodans | 1725 | 0.03|
|Cellulomonas gilvus | 2019 | 0.03|
|Myxococcus xanthus | 5519 | 0.08|
|Myxococcus macrosporus | 4463 | 0.07|
|Stigmatella aurantiaca | 1622 | 0.02|
|Cystobacter fuscus | 2504 | 0.04|
|Archangium gephyra | 3011 | 0.04|
|Chondromyces crocatus | 1719 | 0.03|
|Sorangium cellulosum | 16403 | 0.24|
|Vitreoscilla filiformis | 1746 | 0.03|
|Lysobacter enzymogenes | 44962 | 0.66|
|Stella humosa | 2887 | 0.04| 

### Metadata file
The metadata file should be in two column CSV format (example given below) with a 'csv' extension. Column headers should be present, and should be 'sample_name' and 'grouping'. The actual data you use to group samples can take any format you desire.

**Note.** The data in the column 'sample_name' should correspond to the **shortname** used for each of the abundance input files. If these don't match, the PCA plot will be generated without grouping the data.

| sample_name | grouping |
| --- | --- |
| ERR1329824 | 25 |
| ERR1329825 | 25 |
| ERR1329826 | 25 |
| ERR1329827 | 25 |
| ERR1329828 | 25 |
| ERR1329829 | ancient |
| ERR1329830 | ancient |
| ERR1329831 | word_99 |
| ERR1329832 | word_99 |
| ERR1329833 | word_99 |

## Pipeline integration
To integrate the functionality of abundance_2_PCA into your commandline pipeline, please use the python script 'abundance_PCA_3D_variance.py'. 

### Usage
```bash
   python abundance_PCA_3D_variance.py input directory output directory [metadata] [PCA type] [show_variance]
```

1. input data files and meta data should take the same form as shown above.
2. PCA type should be either '2D' or '3D'.
3. if 'show_variance' is absent, variance will not be shown.


## Author
Dr. Andrew tedder

University of Bradford

## License

This project is licensed under the MIT License - see the LICENSE file for details.
