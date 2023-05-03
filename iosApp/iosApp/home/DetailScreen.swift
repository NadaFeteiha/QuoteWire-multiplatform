//
//  DetailScreen.swift
//  iosApp
//
//  Created by Nada Feteiha on 5/2/23.
//  Copyright © 2023 orgName. All rights reserved.
//

import Foundation


import Foundation
import UIKit
import SwiftUI
import shared


struct DetailScreen: View {
  
     var quoteImage: QuoteImage

    var body: some View {
         AsyncImage(url: URL(string: quoteImage.imageURL)){image in
             image.resizable()
                 .aspectRatio(contentMode: .fit)
                 .cornerRadius(10)
         }placeholder: {
             Color.gray
         }.padding(12)

         Button("share"){

         }
    }
    
}
