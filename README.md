# Link-Image-Viewer
Link-Image-Viewer is app that receives links from Link-Image-Handler, shows images and downloads to internal storage.
## Prerequisites:
Link-Image-Viewer - IS NOT SLAND-ALONE. For use this app you need to install [Limk-Image-Handler](https://github.com/AlexanderRain/link-image-handler) first.
## Specifications:
If image-viewer is opened by clicking the "ok" button with the "test" in image-handler, then image-viewer will store this link in database image-viewer with reference fields, status (1 - downloaded, 2 - error, 3 - unknown) and time (opening time B) and output this picture. If the image-viewer is opened by links from the tab "story", then app show this picture, and when you open the green link - this link will be deleted after 10 seconds from the image-viewer's data base, and show the message that the link has been deleted, even if the image-viewer is closed; and also save this picture in transit /sdcard/BIGDIG/test/B. When opening a red or gray link - their status will update if it has changed. If image-viewer is opened from the lunch list, then a message will display on the screen that image-viewer is not a stand-alone application and will be closed after n seconds.
## License
This project is licensed under the MIT License - see the LICENSE.md file for details.
