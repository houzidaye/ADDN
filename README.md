# ADDN
Android Application for ADDN
usege system platform： android 4.0 
development tool： android stadio
complieSdkVersion 19
buildToolsVersion 25.0.0
minSdkVersion 8
targetSdkVersion 18
gradle 2.3.1

**************************************************************
Project Function
	1.Validating input prarameters.
	2.Passing https request to 'https://dev.addn.org.au/reports/personal'.
	3.Transforming the response to Object.
	4.Show the response in a chart.
Project File Description
    gradle
       The compile file of this project
    app
        libs
            The standard libs folder for app. Include the libs that used for app.
        src
            res
                Resource file folder.
                     Layout
                    	1.Main Activity for validation and input box.(activity_main.xml)
                    	2.Chart Activity for chart show.(activity_my_chart.xml)
                     Values
                    	1.Color values for the colors that used by activities.(colors.xml)
                    	2.Array values for spinner.(array.xml)
                    	3.String values for the string that used by activities.(strings.xml)
                    	4.Style values for the style that used by activities.(styles.xml)
                     Drawable
                    	All the photos that used by ADDN.
            java
                chartEntity
                    Circle.java
                        Circle for the chart circle object.
                    Line.java
                        Line for the chart Line object.
                    Point.java
                        Point for the chart point object.
                    Rectangle.java
                        Rectangle for the chart Rectangle object.
                parameterEntity
                    BodyMassIndexDistribution.java
                        BodyMassIndexDistribution entity for BMI response result and BMI chart show objects.
                    Config.java
                        Config entity for the request parameters.
                    HbA1cDistribution.java
                        HbA1cDistribution entity for HbA1c response result and HbA1c chart show objects.
                    HttpResponseJson.java
                        HttpResponse entity for response result.
                utils
                    Constants.java
                        Constants for this projects.
                ChartDisplayActivity.java
                    Chart Activity for chart show.
                HttpClientHelper.java
                    Http tools for passing request to server and get response back.
                MainActivity.java
                    Main Activity for validation and input box.
                MyChart.java
                    User-defined view for the chart show.
