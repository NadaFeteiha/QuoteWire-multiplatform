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
    
    @StateObject var viewModel = QuoteViewModel()
    
    var body: some View {
        ZStack(alignment: .bottomTrailing) {
            ZStack(alignment: .bottomTrailing)  {
                AsyncImage(url: URL(string: quoteImage.imageURL)){image in
                    image.resizable()
                        .aspectRatio(contentMode: .fill)
                        .edgesIgnoringSafeArea(.all)
                                        .frame(maxWidth: UIScreen.main.bounds.width,
                                               maxHeight: UIScreen.main.bounds.height)
                }placeholder: {
                    Color.gray
                }
            }
            
            VStack(alignment: .trailing, spacing: 16){
            
                Button(action: { Task { await viewModel.onClickFavorite()}}) {
                        Image(systemName: viewModel.quote.isSaved ? "heart.fill" : "heart")
                            .resizable()
                            .frame(width: 36, height: 36)
                            .tint(Color(UIColor(rgb: 0xFF121213)))
                }.padding(12)
                    .background(Color(UIColor(rgb: 0xFFFCF8F5)))
                    .cornerRadius(20, corners: [.topLeft, .bottomRight])
                
                ShareLink(item: URL(string: quoteImage.imageURL)!) {
                    Image(systemName: "square.and.arrow.up")
                        .resizable()
                        .frame(width: 36, height: 36)
                        .tint(Color(UIColor(rgb: 0xFF121213)))
                }.padding(12)
                    .background(Color(UIColor(rgb: 0xFFFCF8F5)))
                    .cornerRadius(20, corners: [.topLeft, .bottomRight])
                
                Button(action: { }) {
                        Image(systemName: "arrow.down.to.line.alt")
                            .resizable()
                            .frame(width: 36, height: 36)
                            .tint(Color(UIColor(rgb: 0xFF121213)))
                    }.padding(12)
                    .background(Color(UIColor(rgb: 0xFFFCF8F5)))
                    .cornerRadius(20, corners: [.topLeft, .bottomRight])

            }.padding(12)
            .padding(.bottom, 40)
            
        }.task {
            viewModel.setQuote(quoteImage: quoteImage )
            await  viewModel.initQuote(quoteId: quoteImage.id)
        }
    }
}


extension UIColor {
   convenience init(red: Int, green: Int, blue: Int) {
       assert(red >= 0 && red <= 255, "Invalid red component")
       assert(green >= 0 && green <= 255, "Invalid green component")
       assert(blue >= 0 && blue <= 255, "Invalid blue component")

       self.init(red: CGFloat(red) / 255.0, green: CGFloat(green) / 255.0, blue: CGFloat(blue) / 255.0, alpha: 1.0)
   }

   convenience init(rgb: Int) {
       self.init(
           red: (rgb >> 16) & 0xFF,
           green: (rgb >> 8) & 0xFF,
           blue: rgb & 0xFF
       )
   }
}
