//
//  QuoteViewModel.swift
//  iosApp
//
//  Created by Nada Feteiha on 5/8/23.
//  Copyright © 2023 orgName. All rights reserved.
//

import Foundation
import shared

struct QuoteImageUI: Identifiable {
    var id: String
    var imageURL: String
    var downloadLink: String
    var isSaved:Bool
    
    init(id: String = "", imageURL: String = "", downloadLink: String = "", isSaved:Bool = false) {
        self.id = id
        self.imageURL = imageURL
        self.downloadLink = downloadLink
        self.isSaved = isSaved
    }
}


extension DetailScreen {
    @MainActor class QuoteViewModel: ObservableObject {
        
        @Published private(set) var quote = QuoteImageUI()
        
        func initQuote(quoteId:String) async{
           await getQuoteFromFavoriteById(quoteId: quoteId)
        }
        
        func setQuote(quoteImage:QuoteImage){
            quote.id = quoteImage.id
            quote.imageURL = quoteImage.imageURL
            quote.downloadLink = quoteImage.downloadLink
        }
        
        func onClickFavorite() async{
             if(quote.isSaved){
                 await removeFromFavorite()
            }else{
                await saveQuoteToFavorite(favoriteQuote: quote)
            }
        }
        
        private func removeFromFavorite() async {
            do {
                quote.isSaved = false
                try await ManageFavoriteQuotesUseCase().removeQuoteFromFavorite(id: quote.id)
            } catch {
                print(error.localizedDescription)
            }
        }
        
       private func saveQuoteToFavorite(favoriteQuote:QuoteImageUI) async {
            do {
                quote.isSaved = true

                try await ManageFavoriteQuotesUseCase().saveFavoriteQuote(quoteImage: QuoteImage(
                    id: favoriteQuote.id,
                    imageURL: favoriteQuote.imageURL,
                    downloadLink: favoriteQuote.downloadLink))
            } catch {
                print(error.localizedDescription)
            }
        }
        
        private func getQuoteFromFavoriteById(quoteId: String) async {
            do {
                if let favoriteQuote = try await ManageFavoriteQuotesUseCase().getFavoriteQuoteByID(id: quoteId) {
                    // favoriteQuote is not nil
                    quote.isSaved = true
                } else {
                    // favoriteQuote is nil
                    quote.isSaved = false
                }
            } catch {
                print(error.localizedDescription)
            }
        }
    }
}

